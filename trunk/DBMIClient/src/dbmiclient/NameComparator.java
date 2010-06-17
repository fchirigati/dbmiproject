/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmiclient;

import java.util.Comparator;

/**
 *
 * @author Fernando Seabra
 */
public class NameComparator implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
        String name1 = t.toString();
        String name2 = t1.toString();

        if (name1.startsWith("Á")) {
            name1 = name1.replaceFirst("Á", "A");
        } else if (name1.startsWith("Í")) {
            name1 = name1.replaceFirst("Í", "I");
        }

        if (name2.startsWith("Á")) {
            name2 = name2.replaceFirst("Á", "A");
        } else if (name2.startsWith("Í")) {
            name2 = name2.replaceFirst("Í", "I");
        }

        return name1.compareTo(name2);
    }
}
