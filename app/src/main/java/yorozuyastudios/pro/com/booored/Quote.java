package yorozuyastudios.pro.com.booored;

public class Quote {
    private String quote;
    private String author;

    Quote(String q,String a)
    {
        quote=q;
        author=a;
    }

    public String getQuote()
    {
        return quote;
    }

    public String getAuthor()
    {
        return author;
    }
}
