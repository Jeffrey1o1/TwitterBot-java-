package twitter;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

/**
 * bot
 */
public class bot {
	  static List<Status> statuses = new ArrayList<>();
	  static List<String> statuse = new ArrayList<>();
	  static Twitter twitter = TwitterFactory.getSingleton();
  
    public static String arrToString(final String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (final String l : arr) {
            sb.append(l);
        }
        return sb.substring(0);
    }

    public static String stringAlt(final String text) {
        int c = 0;
        String low = text.toLowerCase();
        String[] letters = low.split("");
        for (int i = 0; i < letters.length; i++) {
            c++;
            if (Character.isWhitespace(text.charAt(i)))
                c--;
            if (c % 2 == 1)
                letters[i] = letters[i].toUpperCase();
        }
        return arrToString(letters);
    }
    
    public static void fetchTweets(String handle) throws TwitterException {
    	Paging page = new Paging(1,50);
    	int p = 1;
    	while(p<=10) {
    		page.setPage(p);
    		statuses.addAll(twitter.getUserTimeline(handle,page));
    		p++;
    	}
    }
    
    public static void main(String[] args) throws TwitterException {
    	fetchTweets("handle");
    	for(Status l: statuses) {
    		if(!(l.getText().substring(0, 2).equals("RT") || l.getText().substring(0, 1).equals("@")))
    			statuse.add(stringAlt(l.getText()));
    	}
    	for(int i=10;i>=0;i--) {
    		twitter.updateStatus(statuse.get(i) + " @handle");
    	}
    
    }
    
}