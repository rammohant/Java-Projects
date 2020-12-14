/****************************************************************************
 * Ice Cream Shop
 ****************************************************************************
 * A system to keep track of customer’s icecream purchases
 * _____________________________________________________
 * Tara Ram Mohan
 * 29 October 2019
 * CMSC 255 Section 3
 ****************************************************************************/
import javax.smartcardio.Card;
import java.util.ArrayList;

/******************************************************************************
 * Introduce IceCream class
 ****************************************************************************/
public class IceCream {

    /******************************************************************************
     * Declare IceCream attributes of type Enum
     ****************************************************************************/
    private Size size;
    private Flavor flavor;
    private Sauce sauce;
    private Topping topping;
    private ToppingSun toppingSun;

    /******************************************************************************
     * Non-arg constructor
     ****************************************************************************/
    public IceCream() {
        this(Size.SINGLE);
    }

    /******************************************************************************
     * Parameterized constructor
     ****************************************************************************/
    public IceCream(Size size) {
        this.size = size;
        this.flavor = Flavor.VANILLA;
        this.sauce = Sauce.NONE;
        this.topping = Topping.NONE;
        this.toppingSun = toppingSun.NONE;
    }

    /******************************************************************************
     * Mutator methods for IceCream attributes
     ****************************************************************************/
    public void setSize(Size size) {
        this.size = size;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }


    public void setToppingSun(ToppingSun toppingSun) {
        this.toppingSun = toppingSun;
    }

    /******************************************************************************
     * Accessor methods for IceCream attributes
     ****************************************************************************/
    public Size getSize() {
        return size;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Topping getTopping() {
        return topping;
    }

    public ToppingSun getToppingSun() {
        return toppingSun;
    }

    /******************************************************************************
     * ToString method to return each individual IceCream order in correct format
     ****************************************************************************/

    public String toString() {
        return String.format("\n\t" + this.size + "\n\t" + this.flavor + "\n\t" + this.sauce + "\n\t" + this.topping + "\n\t" + this.toppingSun + "\n");
    }
}
