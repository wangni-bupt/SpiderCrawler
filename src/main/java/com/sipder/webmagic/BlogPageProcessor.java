package com.sipder.webmagic;
 

import java.io.File;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sipder.lface.BlogDao;
import com.sipder.lface.impl.BlogDaoImpl;
import com.sipder.urlpojo.BlogInfo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import us.codecraft.webmagic.processor.PageProcessor;
/**
 *
 */
public class BlogPageProcessor implements PageProcessor {
	//抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
	//博文数量
    private  int num = 0;
    //数据库持久化对象，用于将博文信息存入数据库
    private BlogDao blogDao = new BlogDaoImpl();
    private String savepath="E:/java/txt/";
    //用户所需要的数据
    private String data="";
    
	public void process(Page page) {
		// TODO Auto-generated method stub
		//1. 如果是博文列表页面 【入口页面】，将所有博文的详细页面的url放入target集合中。
        // 并且添加下一页的url放入target集合中。
		if (page.getUrl().regex("https://blog\\.hellobi\\.com/hot/weekly\\?page=\\d+").match()) {
			//目标链接
			page.addTargetRequests(page.getHtml().xpath("//h2[@class='title']/a").links().all());
			 //下一页博文列表页链接
			page.addTargetRequests(page.getHtml().xpath("//a[@rel='next']").links().all());
        }
		//2. 如果是博文详细页面
        else {
//            String content1 = page.getHtml().get();
            try {
    
                BlogInfo blog = new BlogInfo();
                //博文标题
                String title = page.getHtml().xpath("//h1[@class='clearfix']/a/text()").get();
                //博文url
                String url = page.getHtml().xpath("//h1[@class='clearfix']/a/@href").get();
                //博文作者
                String author = page.getHtml().xpath("//section[@class='sidebar']/div/div/a[@class='aw-user-name']/text()").get();
                //作者博客地址
                String blogHomeUrl = page.getHtml().xpath("//section[@class='sidebar']/div/div/a[@class='aw-user-name']/@href").get();
                //博文内容，这里只获取带html标签的内容，后续可再进一步处理
                String content = page.getHtml().xpath("//div[@class='message-content editor-style']").get();
                //推荐数(点赞数)
                String recommendNum = page.getHtml().xpath("//a[@class='agree']/b/text()").get();
                //评论数
                String commentNum = page.getHtml().xpath("//div[@class='aw-mod']/div/h2/text()").get().split("��")[0].trim();
                //阅读数（浏览数）
                String readNum = page.getHtml().xpath("//div[@class='row']/div/div/div/div/span/text()").get().split(":")[1].trim();
                //发布时间，发布时间需要处理，这一步获取原始信息
                String time = page.getHtml().xpath("//time[@class='time']/text()").get().split(":")[1].trim();          
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();// 取当前日期。
                cal = Calendar.getInstance();
                String publishTime = null;
                Pattern p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
                Matcher m = p.matcher(time);
                //如果time是“YYYY-mm-dd”这种格式的，则不需要处理
                if (m.matches()) {
                    publishTime = time;
                } else if (time.contains("天")) {  //如果time包含“天”，如1天前，
                    int days = Integer.parseInt(time.split("天")[0].trim());//则获取对应的天数
                    cal.add(Calendar.DAY_OF_MONTH, -days);// 取当前日期的前days天.
                    publishTime = df.format(cal.getTime()); //并将时间转换为“YYYY-mm-dd”这个格式
                } else {//time是其他格式，如几分钟前，几小时前，都为当日日期
                    publishTime = df.format(cal.getTime());
                }
                PrintWriter pw=new PrintWriter(new File(savepath+title+".txt"));
           /*     pw.println(title);
                pw.println(author);
                pw.println(url);
                pw.println(blogHomeUrl);
                pw.println(commentNum);
                pw.println(recommendNum);
                pw.println(readNum);
                pw.println(content);
                pw.println(publishTime);
                pw.close();   */
                
                pw.println(title);
                switch(data) {
                case "博文url": pw.println("url"+url);break;
                case "作者博客地址": pw.println("blogHomeUrl"+blogHomeUrl);break;
                case "评论数": pw.println("commentNum"+commentNum);break;
                case "推荐数": pw.println("recommendNum"+recommendNum);break;
                case "阅读数": pw.println("readNum"+readNum);break;
                case "博文内容": pw.println("content"+content);break;
                case "发布时间": pw.println("publishTime"+publishTime);break;
                case "博文作者": pw.println("author"+author);break;
                case "所有" : pw.println(title+"\n"+title+"\n"+author+"\n"+url+"\n"+blogHomeUrl+"\n"+commentNum+"\n"+recommendNum+"\n"+readNum+"\n"+content+"\n"+publishTime);break;
                }
                    //打印所需数据
                pw.close();
                
                
                
                //对象赋值
                blog.setUrl(url);
                blog.setTitle(title);
                blog.setAuthor(author);
                blog.setBlogHomeUrl(blogHomeUrl);
                blog.setCommentNum(commentNum);
                blog.setRecommendNum(recommendNum);
                blog.setReadNum(readNum);
                blog.setContent(content);
                blog.setPublishTime(publishTime);
                num++;
                System.out.println("num:" + num + " " + blog.toString());
                blogDao.saveBlog(blog);//保存博文信息到数据库
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		 return this.site;
	}
	
	public int getnum() {
		return this.num;
	}
	
	public void setdata(String data) {
		this.data=data;
	}

 /*   public static void main(String[] args) throws Exception {
        long startTime, endTime;
        System.out.println("========爬虫启动=========");
        startTime = new Date().getTime();
        Spider.create(new BlogPageProcessor()).addUrl("https://blog.hellobi.com/hot/weekly?page=1").thread(5).run();
        endTime = new Date().getTime();
        System.out.println("========爬虫结束=========");
        System.out.println("一共爬到" + num + "篇博客！用时为：" + (endTime - startTime) / 1000 + "s");
    }
    */
}
