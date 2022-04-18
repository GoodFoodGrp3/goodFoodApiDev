package com.goodfood.api.request;

public class CreateCommentForm
{
    private int id;
    private String content;


    // ***************
    // CONSTRUCTOR
    // ***************

    public CreateCommentForm(int id, String content)
    {
        this.id = id;
        this.content = content;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
