package com.aaron.util.codestatistics;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-06-18
 */
public class StatisticsResult
{
    private AtomicLong totalCodeLines = new AtomicLong();

    private AtomicLong commentLines = new AtomicLong();

    private AtomicLong validCodeLines = new AtomicLong();

    private AtomicLong validCodeLinesWithoutBlank = new AtomicLong();

    private AtomicLong validCodeLinesWithoutImport = new AtomicLong();


    public AtomicLong getTotalCodeLines()
    {
        return totalCodeLines;
    }


    public void setTotalCodeLines(AtomicLong totalCodeLines)
    {
        this.totalCodeLines = totalCodeLines;
    }


    public AtomicLong getCommentLines()
    {
        return commentLines;
    }


    public void setCommentLines(AtomicLong commentLines)
    {
        this.commentLines = commentLines;
    }


    public AtomicLong getValidCodeLines()
    {
        return validCodeLines;
    }


    public void setValidCodeLines(AtomicLong validCodeLines)
    {
        this.validCodeLines = validCodeLines;
    }


    public AtomicLong getValidCodeLinesWithoutBlank()
    {
        return validCodeLinesWithoutBlank;
    }


    public void setValidCodeLinesWithoutBlank(AtomicLong validCodeLinesWithoutBlank)
    {
        this.validCodeLinesWithoutBlank = validCodeLinesWithoutBlank;
    }


    public AtomicLong getValidCodeLinesWithoutImport()
    {
        return validCodeLinesWithoutImport;
    }


    public void setValidCodeLinesWithoutImport(AtomicLong validCodeLinesWithoutImport)
    {
        this.validCodeLinesWithoutImport = validCodeLinesWithoutImport;
    }


    @Override
    public String toString()
    {
        return "统计结果" +
                "\r\n\t总共代码行数：" + totalCodeLines.get() + "行" +
                "\r\n\t有效代码行数：" + validCodeLines.get() + "行" +
                "\r\n\t有效代码行数(不包括导包)：" + validCodeLinesWithoutImport.get() + "行" +
                "\r\n\t有效代码行数(不包括导包和空行)：" + validCodeLinesWithoutBlank.get() + "行" +
                "\r\n\t总共注释行数：" + commentLines.get() + "行";
    }
}
