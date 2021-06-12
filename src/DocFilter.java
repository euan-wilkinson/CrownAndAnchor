/*
 * This class was taken from here: https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
 * It was not written by me, but by StackOverflow user 'Hovercraft Full Of Eels' on 19/06/12.
 * I have used this code in my game to restrict the characters a player may enter when placing bets to integers only.
 * Nothing below this comment is my own work. For full Harvard reference, see Reference List in the project folder.
 * 
 *  - Euan Wilkinson S5083285
 */

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


class MyIntFilter extends DocumentFilter {
   @Override
   public void insertString(FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (test(sb.toString())) {
         super.insertString(fb, offset, string, attr);
      } else {
         // warn the user and don't allow the insert
      }
   }

   private boolean test(String text) {
      try {
         Integer.parseInt(text);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (test(sb.toString())) {
         super.replace(fb, offset, length, text, attrs);
      } else {

      }

   }

   @Override
   public void remove(FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);

      if(sb.toString().length() == 0) 
      { 
    	  super.replace(fb, offset, length, "", null); 
      } 
      else 
      { 
    	  if (test(sb.toString())) 
    	  { 
    		  super.remove(fb, offset, length); 
    	  } 
    	  
    	  else 
    	  {
    	  
    	  }
      }
      }
}
