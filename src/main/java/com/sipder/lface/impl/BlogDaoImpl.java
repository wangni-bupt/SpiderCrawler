
package com.sipder.lface.impl;

import java.util.ArrayList;

import java.util.List;

import com.sipder.lface.BlogDao;
import com.sipder.urlpojo.BlogInfo;
import com.sipder.util.DBHelper; 
/*
 *  blog中信息填入mysql中
 */

public class BlogDaoImpl implements BlogDao {

	public int saveBlog(BlogInfo blog) {
		 DBHelper dbhelper = new DBHelper();
	        StringBuffer sql = new StringBuffer();
	        sql.append("INSERT INTO hot_weekly_blogs(url,title,author,readNum,recommendNum,blogHomeUrl,commentNum,publishTime,content)")
	                .append("VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? ) ");
	        //设置 sql values 的值ֵ
	        List<String> sqlValues = new ArrayList<String>();
	        sqlValues.add(blog.getUrl());
	        sqlValues.add(blog.getTitle());
	        sqlValues.add(blog.getAuthor());
	        sqlValues.add(""+blog.getReadNum());
	        sqlValues.add(""+blog.getRecommendNum());
	        sqlValues.add(blog.getBlogHomeUrl());
	        sqlValues.add(""+blog.getCommentNum());
	        sqlValues.add(blog.getPublishTime());
	        sqlValues.add(blog.getContent());
	        int result = dbhelper.executeUpdate(sql.toString(), sqlValues);
	        return result;
	}
	
}
