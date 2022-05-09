package com.ithome.web.Bean;

public class Comments {
    private int Commentid;
    private int productCode;
    private String Comment1_Am;
    private String Comment2_Am;
    private String Comment3_Am;
    private String Comment1_En;
    private String Comment2_En;
    private String Comment3_En;
    private String comment1_Ru;
    private String comment2_Ru;
    private String comment3_Ru;

    public Comments(int commentid, int productCode, String comment1_Am, String comment2_Am, String comment3_Am, String comment1_En, String comment2_En, String comment3_En, String comment1_Ru, String comment2_Ru, String comment3_Ru) {
        Commentid = commentid;
        this.productCode = productCode;
        Comment1_Am = comment1_Am;
        Comment2_Am = comment2_Am;
        Comment3_Am = comment3_Am;
        Comment1_En = comment1_En;
        Comment2_En = comment2_En;
        Comment3_En = comment3_En;
        this.comment1_Ru = comment1_Ru;
        this.comment2_Ru = comment2_Ru;
        this.comment3_Ru = comment3_Ru;
    }

    public Comments(int productCode, String comment1_Am, String comment2_Am, String comment3_Am, String comment1_En, String comment2_En, String comment3_En, String comment1_Ru, String comment2_Ru, String comment3_Ru) {
        this.productCode = productCode;
        Comment1_Am = comment1_Am;
        Comment2_Am = comment2_Am;
        Comment3_Am = comment3_Am;
        Comment1_En = comment1_En;
        Comment2_En = comment2_En;
        Comment3_En = comment3_En;
        this.comment1_Ru = comment1_Ru;
        this.comment2_Ru = comment2_Ru;
        this.comment3_Ru = comment3_Ru;
    }

    public Comments() {
    }

    public Comments(String comment1_Am, String comment2_Am, String comment3_Am, String comment1_En, String comment2_En, String comment3_En, String comment1_Ru, String comment2_Ru, String comment3_Ru) {
        Comment1_Am = comment1_Am;
        Comment2_Am = comment2_Am;
        Comment3_Am = comment3_Am;
        Comment1_En = comment1_En;
        Comment2_En = comment2_En;
        Comment3_En = comment3_En;
        this.comment1_Ru = comment1_Ru;
        this.comment2_Ru = comment2_Ru;
        this.comment3_Ru = comment3_Ru;
    }

    public Comments(String comment1Am, String comment2Am, String comment3Am) {
        this.Comment1_Am = comment1Am;
        this.Comment2_Am = comment2Am;
        this.Comment3_Am = comment3Am;

    }

    public int getCommentid() {
        return Commentid;
    }

    public void setCommentid(int commentid) {
        Commentid = commentid;
    }

    public String getComment1_Am() {
        return Comment1_Am;
    }

    public void setComment1_Am(String comment1_Am) {
        Comment1_Am = comment1_Am;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getComment2_Am() {
        return Comment2_Am;
    }

    public void setComment2_Am(String comment2_Am) {
        Comment2_Am = comment2_Am;
    }

    public String getComment3_Am() {
        return Comment3_Am;
    }

    public void setComment3_Am(String comment3_Am) {
        Comment3_Am = comment3_Am;
    }

    public String getComment1_En() {
        return Comment1_En;
    }

    public void setComment1_En(String comment1_En) {
        Comment1_En = comment1_En;
    }

    public String getComment2_En() {
        return Comment2_En;
    }

    public void setComment2_En(String comment2_En) {
        Comment2_En = comment2_En;
    }

    public String getComment3_En() {
        return Comment3_En;
    }

    public void setComment3_En(String comment3_En) {
        Comment3_En = comment3_En;
    }

    public String getComment1_Ru() {
        return comment1_Ru;
    }

    public void setComment1_Ru(String comment1_Ru) {
        this.comment1_Ru = comment1_Ru;
    }

    public String getComment2_Ru() {
        return comment2_Ru;
    }

    public void setComment2_Ru(String comment2_Ru) {
        this.comment2_Ru = comment2_Ru;
    }

    public String getComment3_Ru() {
        return comment3_Ru;
    }

    public void setComment3_Ru(String comment3_Ru) {
        this.comment3_Ru = comment3_Ru;
    }
}
