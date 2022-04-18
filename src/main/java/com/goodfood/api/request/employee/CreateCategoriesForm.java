package com.goodfood.api.request.employee;

public class CreateCategoriesForm
{
    private int id;
    private String categoryName;
    private String textDescription;
    private String htmlDescription;
    private String image;


    // ***************
    // CONSTRUCTOR
    // ***************

    public CreateCategoriesForm(int id, String categoryName, String textDescription, String htmlDescription, String image)
    {
        this.id = id;
        this.categoryName = categoryName;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
        this.image = image;
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

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getTextDescription()
    {
        return textDescription;
    }

    public void setTextDescription(String textDescription)
    {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription()
    {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription)
    {
        this.htmlDescription = htmlDescription;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
}
