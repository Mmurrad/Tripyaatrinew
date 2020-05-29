package com.example.tripyaatrinew.model;

public class UploadDocument {
    String pdfname;
    String pdf;

    public UploadDocument() {
    }

    public UploadDocument(String pdfname, String pdf) {
        this.pdfname = pdfname;
        this.pdf = pdf;
    }

    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
