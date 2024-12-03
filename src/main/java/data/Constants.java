package data;

public class Constants {

    //      HolidayPageTests
    public static final String
            SWOOP_PAGE = "https://swoop.ge/",
            REST_BUTTON_XPATH = "//a[@href='/category/24/dasveneba/' and .//img[contains(@alt, 'დასვენება')]]",
            SIDEBAR_REST_XPATH = "//form//div//h4[text() = 'დასვენება']",
            PRICE_ORDER_DROPDOWN_XPATH = "//p[text()='შესაბამისობით']",
            PRICE_DESCENDING_XPATH = "//p[text() = 'ფასით კლებადი']",
            PRICE_ASCENDING_XPATH = "//p[text() = 'ფასით ზრდადი']",
            ACTUAL_PRICES_XPATH = "//h4[contains(@class, 'text-primary_black-100-value text-2md leading-5 font-tbcx-bold')]",
            NEXT_BUTTON_XPATH = "//img[@alt='right arrow']/parent::div",
            JS_CLICK = "arguments[0].click();",
            JS_SCROLL_TO_ELEMENT = "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
            MOUNTAIN_FILTER_XPATH = "//h5[text()='მთის კურორტები']",
            FULLPAY_RADIO_BTN_XPATH = "//label[span[text()='სრული გადახდა']]",
            FULLPAY_RADIO_BTN_LABEL_XPATH = "//div[p[text()='სრული გადახდა']]",
            PRICE_RANGE_INPUT_FROM_XPATH = "//p[text()='დან']/following-sibling::input[@type='text']",
            PRICE_RANGE_INPUT_TO_XPATH = "//p[text()='მდე']/following-sibling::input[@type='text']",
            PRICE_RANGE_ACCEPT_XPATH = "//button[@data-testid='secondary-button']",
            MAX_PRICE_ERR_MSG = "first element isn't maximum price",
            MIN_PRICE_ERR_MSG = "first price isn't minimum";


    //      LandingPageTests
    public static final String
            CATEGORY_XPATH = "//button[@data-testid='outline-button' and .//img[@alt='Frame.svg']]",
            CATEGORY_SPORT_XPATH = "//a[@href='/category/110/sporti/']//div[contains(@class, 'cursor-pointer')]",
            SPORT_CARTING_XPATH = "//h4[contains(@class, 'text-primary_black-100-value') and text()='კარტინგი']",
            EXPECTED_URL = "https://swoop.ge/category/2058/sporti/kartingi/",
            BREADCRUMB_XPATH = "//nav[@class='flex items-center flex-nowrap whitespace-nowrap py-2']",
            LOGO_XPATH = "//a[@href='/' and .//img[@alt='swoop']]",
            BREADCRUMB_ERR_MSG = "breadcrumb doesn't contain word კარტინგი",
            CURRENT_PAGE_ERR_MSG = "current page isn't home page";


    //              MoviePageTests
    public static final String
            MOVIES_SECTION_XPATH = "//a[@href='/movies/' and .//img[@src='https://cdn.swoop.ge/images/icons/kino.png']]",
            CAVEA_EAST_POINT_XPATH = "//h3[@class='text-2md leading-5 font-tbcx-medium text-nowrap' and text()='კავეა ისთ ფოინთი']",
            ACTUAL_MOVIES_XPATH = "//h3[@class='text-primary_black-100-value text-md leading-5 font-tbcx-medium cursor-pointer hover:underline underline-offset-6']",
            WEEKDAYS_XPATH = "//p[text()='კვი']/parent::div/parent::div/parent::div/div[not(contains(@class, 'pointer-events-none'))]",
            LAST_SESSION_OF_CAVEA_XPATH = "//h3[text()='კავეა ისთ ფოინთი']/parent::div/parent::div/div/div/div/div[last()]",
            COOKIE_ACCEPT_XPATH = "//button[.//p[text()='ვეთანხმები']]",
            DATE_TIME = "//h3[text()='კავეა ისთ ფოინთი']/parent::div/parent::div/div/div/div/div[last()]/div[last()]/p",
            MOVIE_NAME_IN_POPUP_XPATH = "//div[@role='dialog']//h2/parent::div[not(contains(@class, 'hidden'))]/h2",
            CINEMA_NAME_IN_POPUP_XPATH = "//div[@role='dialog']//h2/following-sibling::div/p[not(contains(@class, '70'))][1]",
            DATE_AND_TIME_POPUP_XPATH = "//div[@role='dialog']//h2/following-sibling::div/p[not(contains(@class, '70'))][2]",
            EMPTY_SEAT_CSS_SEL = "div.relative svg path:not([fill='#AAAAAA']):nth-of-type(2)",
            LEGEND_CHART_XPATH = "//div[contains(@class, 'green-100')]",
            ACCOUNT_CREATION_XPATH = "//div[contains(@class, 'create')]//a[contains(@href, 'register')]",
            EMAIL_ERROR_MESSAGE_XPATH = "//p[@class='error wrong' and @id='input-error-email']",
            MALE_RADIO_BTN_XPATH = "//label[@for='Gender1']",
            MY_BIRTHYEAR_XPATH = "//ul//li[text()='1998']",
            CHOOSE_BIRTHYEAR_DROPDOWN_XPATH = "//select/following-sibling::span",
            LAST_EL_OF_CAVER_ERR_MSG = "Could not find the last element after clicking all available dates.",
            INVALID_EMAIL_ERR = "Email validation message is not displayed",
            TIMES_ERR_MSG = "Times doesn't match",
            DATES_ERR_MSG = "dates doesn't match",
            CINEMA_ERR_MSG = "cinema doesn't match",
            MOVIE_TITLE_ERR_MSG = "movie titles doesn't match";

}
