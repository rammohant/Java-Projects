import bridges.base.ElementVisualizer;
import bridges.connect.Bridges;
import bridges.base.Array1D;
import bridges.base.Element;
import java.lang.Math;


public class Setup {
	public static void main(String[] args) throws Exception {

	    /* Initialize a Bridges connection with your credentials */
	    /* TODO: plug your own BRIDGES credentials */
	    Bridges bridges = new Bridges(0, "rammohant", "24798395262956);
			int random3 = (int) (Math.random() *256);
			float random4 = (float) Math.random();

			ElementVisualizer hi = arr.getElement(i).getVisualizer();
			hi.setColor(random1, random2, random3, random4);

		}

	    /* Tell BRIDGES which data structure to visualize */
	    bridges.setDataStructure(arr);

	    /* Visualize the Array */
	    bridges.visualize();
	}
}