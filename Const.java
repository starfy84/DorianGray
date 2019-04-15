/**
 * Author(s): Matthew Mach, Dereck Tu, William Xu
 * Version: 2.0
 * Date: April 07, 2019
 *
 * Modifications:
 *
 * Description:
 * 
 **/
import java.util.*;
import javafx.scene.image.*;
public final class Const{
	protected static final String HIGHSCORE_PATH = "saves/Highscores";
	protected static final String MAIN_MENU_PATH = "/Images/main menu/";
	protected static final String GAME_PATH = "/Images/game/";
	protected static final String CARD_PATH = "/Images/card/";
	protected static final String CARD_FRONT_PATH = "/Images/card/front/";
	protected static final String CARD_BACK_PATH = "/Images/card/back/";
	protected static final String TITLE = "The Changing of Dorian Gray";
	protected static final int LENGTH = 1280;
	protected static final int WIDTH = 720;
	protected static boolean GAME_DEBUG = false;
	protected static boolean MENU_DEBUG = false;
	protected static boolean DECK_DEBUG = false;
	protected static final boolean MASTER_DEBUG = false;
	protected static HashMap<String, Card> CARD_CACHE = new HashMap<String, Card>();
	protected static HashMap<String, Deck> DECK_CACHE = new HashMap<String, Deck>();
	protected static HashMap<String, ImageView> CHARS = new HashMap<String, ImageView>();
	protected static HashMap<String, ImageView> BACKGROUNDS = new HashMap<String, ImageView>();
}