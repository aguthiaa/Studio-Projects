package com.peter.post.models;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    /**
     * No args constructor for use in serialization
     *
     */
    public Post() {
    }

    /**
     *
     * @param id
     * @param body
     * @param title
     * @param userId
     */
    public Post(Integer userId, Integer id, String title, String body) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Post withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post withBody(String body) {
        this.body = body;
        return this;
    }

}
