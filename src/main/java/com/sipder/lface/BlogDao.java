package com.sipder.lface;

import com.sipder.urlpojo.BlogInfo;

/**

 * 接口

 * 

 */

public interface BlogDao {
    /**
     * 保存blog的信息

     * @param blog

     * @return

     */
    public int saveBlog(BlogInfo blog);
}
