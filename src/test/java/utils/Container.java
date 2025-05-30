package utils;

import com.booking.page.Homepage;
import com.booking.page.ReservePage;
import com.booking.page.SearchResultsPage;

import java.util.Optional;


public class Container {

    public Homepage homepage;
    public TestBeforeNAfterHooks testBeforeNAfterHooks;
    public SearchResultsPage searchResultsPage;
    public ReservePage reservePage;
    public Optional<String> parentWindowID;


}
