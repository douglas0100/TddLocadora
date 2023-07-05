import junit.framework.TestCase;

public class Testes extends TestCase {
    
    Customer client;

    protected void setUp() throws Exception {
        client = new Customer("John");
    }

    public void testNameCriation(){
        String result = client.statement();
        assertContain(result, "Rental Record for John");
    }

    public void testOneRegularOneDay(){
        rentMovie("Indiana Jones", Movie.REGULAR, 1);
        String result = client.statement();
        assertContain(result, "Amount owed is 2.0");
        assertContain(result, "You earned 1 frequent renter points");
    }

    public void testOneRegularTreeDays(){
        rentMovie("Indiana Jones", Movie.REGULAR, 3);
        String result = client.statement();
        assertContain(result, "Amount owed is 3.5");
        assertContain(result, "You earned 1 frequent renter points");
    }

     public void testOneChildrensFiveDays(){
        rentMovie("Vingadores", Movie.CHILDRENS, 5);
        String result = client.statement();
        assertContain(result, "Amount owed is 4.5");
        assertContain(result, "You earned 1 frequent renter points");
    }

    public void testOneNewReleaseOneDay(){
        rentMovie("Procurando Nemo", Movie.NEW_RELEASE, 1);
        String result = client.statement();
        assertContain(result, "Amount owed is 3");
        assertContain(result, "You earned 1 frequent renter points");
    }
    

    private void rentMovie(String title, int type, int days){
        Movie movie = new Movie(title, type);
        Rental rent = new Rental(movie, days);

        client.addRental(rent);
    }

    private void assertContain(String result, String content){
        assertTrue(result.indexOf(content) >= 0);
    }
    

}
