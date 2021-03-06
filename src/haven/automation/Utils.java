package haven.automation;

import haven.Equipory;
import haven.GameUI;
import haven.WItem;


public class Utils {
    private static final int HAND_DELAY = 5;
    private static final int PROG_ACT_DELAY = 8;
    private static final int PROG_FINISH_DELAY = 70;

    public static boolean waitForEmptyHand(GameUI gui, int timeout, String error) throws InterruptedException {
        int t = 0;
        while (gui.vhand != null) {
            t += HAND_DELAY;
            if (t >= timeout) {
                gui.error(error);
                return false;
            }
            try {
                Thread.sleep(HAND_DELAY);
            } catch (InterruptedException ie) {
                throw ie;
            }
        }
        return true;
    }

    public static boolean waitForOccupiedHand(GameUI gui, int timeout, String error) throws InterruptedException {
        int t = 0;
        while (gui.vhand == null) {
            t += HAND_DELAY;
            if (t >= timeout) {
                gui.error(error);
                return false;
            }
            try {
                Thread.sleep(HAND_DELAY);
            } catch (InterruptedException ie) {
                throw ie;
            }
        }
        return true;
    }

    public static boolean waitForProgressFinish(GameUI gui, int timeout, String error) throws InterruptedException {
        int t = 0;
        while (gui.prog == -1) {
            t += PROG_ACT_DELAY;
            if (t >= timeout)
                break;
            try {
                Thread.sleep(PROG_ACT_DELAY);
            } catch (InterruptedException ie) {
                throw ie;
            }
        }

        t = 0;
        while (gui.prog != -1) {
            t += PROG_FINISH_DELAY;
            if (t >= timeout) {
                gui.error(error);
                return false;
            }
            try {
                Thread.sleep(PROG_FINISH_DELAY);
            } catch (InterruptedException ie) {
                throw ie;
            }
        }
        return true;
    }

    public static boolean waitForOccupiedEquiporySlot(GameUI gui, int slot, int timeout, String error) throws InterruptedException {
        Equipory e = gui.getequipory();
        int t = 0;
        while (e.quickslots[slot] == null) {
            t += HAND_DELAY;
            if (t >= timeout) {
                gui.error(error);
                return false;
            }
            try {
                Thread.sleep(HAND_DELAY);
            } catch (InterruptedException ie) {
                throw ie;
            }
        }
        return true;
    }
}
