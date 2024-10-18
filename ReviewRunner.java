import java.io.File;
import java.util.Scanner;

class ReviewRunner {
    public static String[] folderContents(String folderName) { 
      File folder = new File(folderName);
      File[] files = folder.listFiles(); 

      String[] results = new String[files.length];

      for (int i = 0; i < files.length; i++) {
        File textFile = files[i];
        String temp = "";

          try {
            Scanner input = new Scanner(textFile);
            
            while(input.hasNext()) {
              temp = temp.concat(input.next() + " ");
            }

            input.close();
            temp = temp.trim();
          }

          catch(Exception e){
            System.out.println("Unable to locate " + textFile);
          }

        results[i] = temp;
    }
    return results;
  }

  public static void main(String[] args) 
  {
    String[] windowsReviews = folderContents("windowsReviews");
    String[] macReviews = folderContents("macReviews");

    int windowsSentimentAvg = 0;
    for (String windowsReview : windowsReviews) {
      for (String word : windowsReview.split(" ")) {
        windowsSentimentAvg += Review.sentimentVal(Review.removePunctuation(word));
      }
    }
    windowsSentimentAvg /= windowsReviews.length;

    int macSentimentAvg = 0;
    for (String macReview : macReviews) {
      for (String word : macReview.split(" ")) {
        macSentimentAvg += Review.sentimentVal(Review.removePunctuation(word));
      }
    }
    macSentimentAvg /= windowsReviews.length;

    if (macSentimentAvg > windowsSentimentAvg) {
      System.out.println("Mac average sentiment value is higher than Windows by " + (macSentimentAvg - windowsSentimentAvg) + ".");
    } else {
      System.out.println("Windows average sentiment value is higher than Mac by " + (windowsSentimentAvg - macSentimentAvg) + ".");
    }

    System.out.println("Windows value: " + windowsSentimentAvg);
    System.out.println("Mac value: " + macSentimentAvg);
  }
}