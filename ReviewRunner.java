import java.io.File;
import java.util.Scanner;

class ReviewRunner {
    /**
    * Take a folder path and return a list of the text contained in the items within.
    *
    * Accepts a String that represents a path, which will be internally converted to a File object.
    * Will attempt to read all paths within the folder, but will not recurse into folders within.
    *
    * @param folderName A string representing a local or global path.
    * @return A String list containing the textual contents of all files within the folder given.
    */
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
    int windowsWords = 0;
    double windowsSentimentAvg = 0;
    for (String windowsReview : windowsReviews) {
      for (String word : windowsReview.split(" ")) {
        double wordSentiment = Review.sentimentVal(Review.removePunctuation(word));
        windowsSentimentAvg += wordSentiment;
        if (wordSentiment != 0) {
          windowsWords++;
        }
      }
    }
    windowsSentimentAvg /= windowsWords;

    int macWords = 0;
    double macSentimentAvg = 0;
    for (String macReview : macReviews) {
      for (String word : macReview.split(" ")) {
        double wordSentiment = Review.sentimentVal(Review.removePunctuation(word));
        macSentimentAvg += wordSentiment;
        if (wordSentiment != 0) {
          macWords++;
        }
      }
    }
    macSentimentAvg /= macWords;

    if (macSentimentAvg > windowsSentimentAvg) {
      System.out.println("Mac average sentiment value is higher than Windows by " + (macSentimentAvg - windowsSentimentAvg) + ".");
    } else {
      System.out.println("Windows average sentiment value is higher than Mac by " + (windowsSentimentAvg - macSentimentAvg) + ".");
    }

    System.out.println("Windows value: " + windowsSentimentAvg);
    System.out.println("Mac value: " + macSentimentAvg);
  }
}
