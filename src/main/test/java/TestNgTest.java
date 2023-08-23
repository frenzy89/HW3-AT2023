import org.testng.Assert;
import org.testng.annotations.Test;
import parser.XMLParser;
import utilities.Iterator;


public class TestNgTest {
    @Test(groups = {"negative"})
    public void testNoFileUnsucessful() {
        Assert.assertThrows(NullPointerException.class, () -> new XMLParser(null));
    }

    @Test(groups = {"positive"})
    public void testValidFileSucessful() {
        XMLParser parser1 = new XMLParser("C:\\Users\\max89\\HW3\\src\\main\\test\\resourses\\newDocument.xml");
        parser1.parseDocument();
        Assert.assertEquals(parser1.getErrors().isEmpty(), true);
    }

    @Test(groups = {"negative"})
    public void testUnvalidFileUnclosedEndTagUnsucessful() {//Closing tag is not done
        XMLParser parser1 = new XMLParser("C:\\Users\\max89\\HW3\\src\\main\\test\\resourses\\purchaseOrder.xml");
        if (parser1.getErrors().isEmpty()) {
            System.out.println("Valid xml");
        } else {
            Iterator<String> errorsIter = parser1.getErrors().iterator();
            while (errorsIter.hasNext()) {
                System.out.println(errorsIter.next());

                Assert.assertEquals(errorsIter.next(), "The tag <PurchaseOrder closed incorrectly. Missing >\n" +
                        "All data should be inside of the root tag");
            }
        }
    }

    @Test(groups = {"negative"})
    public void testUnclosedStartTagUnsucessful() {
        XMLParser parser1 = new XMLParser("C:\\Users\\max89\\HW3\\src\\main\\test\\resourses\\unclosedStartTag.xml");
        if (parser1.getErrors().isEmpty()) {
            System.out.println("Valid xml");
        } else {
            Iterator<String> errorsIter = parser1.getErrors().iterator();
            while (errorsIter.hasNext()) {
                System.out.println(errorsIter.next());

                Assert.assertEquals(errorsIter.next(), "Document should start from root tag or instruction. ");
            }
        }
    }

    @Test(groups = {"negative"})
    public void testUnclosedInstructionTagUnsucessful() {
        XMLParser parser1 = new XMLParser("C:\\Users\\max89\\HW3\\src\\main\\test\\resourses\\unclosedInstructionTag.xml");
        if (parser1.getErrors().isEmpty()) {
            System.out.println("Valid xml");
        } else {
            Iterator<String> errorsIter = parser1.getErrors().iterator();
            while (errorsIter.hasNext()) {
                System.out.println(errorsIter.next());

                Assert.assertEquals(errorsIter.next(), "Instruction tag is not closed. ");
            }
        }
    }

    @Test(groups = {"negative"})
    public void testTotallyUnvalidFileUnsucessful() {
        XMLParser parser1 = new XMLParser("C:\\Users\\max89\\HW3\\src\\main\\test\\resourses\\allFinalTagsOmitted.xml");
        parser1.parseDocument();
        Assert.assertNotEquals(parser1.getErrors().isEmpty(), true);
    }
}