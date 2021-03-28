The project "Pre-Paid Card System" is made by 
- Eva Tagarro López de Ayala @170183
- José Manuel Díaz Urraco @170085
- Angelika Krolikowska @170042

## Pre-Paid Card System

#### Description
The project consists in a Java program that manages pre-paid cards and makes possible to pay with them. It includes a simple interface and the following operations:
>- (a) Buy a card
- (b) Pay
- (c) Charge money
- (d) Change PIN
- (e) Consult balance
- (f) Consult movements


#### How to use the interface
Go to the folder "src/main/java/controller/Controller.java" and run it. Once done, it will display a window with the actions you can do. You may choose between (a) and (f), mentioned in the previous paragraph.
After choosing the operation, the data you introduce, will be saved in the files ".xml". You can see them here 
[xml](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system).

If you want to see the interface's code in Gitlab, go to folder named "prepaid-system" [prepaid-system](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system). 
Then click on "src" folder [src](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system/src) and go to "main" [main](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system/src/main).
After that, go to the folder named "java" [java](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system/src/main/java) and click on "prepaidsystemgui" 
[prepaidsystemgui](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system/src/main/java/prepaidsystemgui). 
You can click here [prepaidsystemgui](http://costa.ls.fi.upm.es/gitlab/170183/pproject/tree/master/prepaid-system/src/main/java/prepaidsystemgui) to visit directly.


#### About the interface

>Controller.java : contains calls to windows for all basic operations (a to f) described at the beginning of this page.

We have implemented a controller (main funcion inside) and several classes for the different possible windows that can come out when you are doing the required actions of the system.
The controller has a class that serves to invoke the main interface screen, that is, the full-featured menu. 

The application must be executed running as a Java application using the controller because the main funtion is inside it.

We have also implemented their buttons and their windows invoked. The classes are:

- BuyCardWindow.java
- ChangePinWindow.java
- ChargeMoneyWindow.java
- ChargePayTicket.java
- ConsultBalanceTicketWindow.java
- ConsultBalanceWindow.java
- ConsultMovementsWindow.java
- MainWindow.java
- MovementListWindow.java
- PayWindow.java
- BuyCardTicketWindow.java
- GenericFunctions.java

These class are included in the package prepaidsystemgui in order to avoid test.

>DataExchange.java : contains calls to windows for all basic operations (a to f) described at the beginning of this page.

In the same package, we have included the class called DataExchange.java 
that has all the future arguments for the methods and is defined as a way to exchange date between windows and methods.


#### Project's methods 
- We have programmed the system with SHA259 function to save the pin number, as requested. These methods belong to PrepaidSystem.java.

>PrepaidSystem.java : contains all basic operations (a to f) described at the beginning of this page. 

###### (a) public static void buyCard (DataExchange data) throws CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException

User can buy a new card. Returns a message on the screen showing that operation has been done succesfully. The user must introduce the data. The expiration date is given by the method object.plusYears(1).
It returns a CreateSystemException when the name or surname is blank or the pin numbers have not four digits. Also returns a NumberFormatException when the introduced amount is not a number or is zero.

###### (b) public static void pay (DataExchange data) throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException

User can pay an amount of money. Returns a message on the screen showing that the payment has been done correctly. The user must introduce his/her data. This method checks if the card is registered in the system, if the pin is correct
, if the card has enough money to pay and if the card has not expired. If not, returns a CreateSystemException. Returns a CreateSystemException too, when the amount is not greater than zero.
The amount is checked too. If the amount is not a number, returns a NumberFormatException.

###### (c) public static void chargeMoney (DataExchange data) throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException 

User can charge an amount of money in the card. Returns a message on the screen showing that operation has been done succesfully. The user must introduce his/her data. 
This method checks the same operations that the previous method 'pay' and returns a CreateSystemException if one of the conditions checked is not satisfied.

###### (d) public static void changePin(DataExchange data) throws CreateNumberFormatException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateSystemException

User can change the pin by introducing his/her data. Method checks if the introduced card is registered in the system, if the pin is correct and if the pin is not the same as the current one.
Otherwise, returns a CreateSystemException. Throws a CreateNumberFormatException if the pin has not four digits.

###### (e) public static String consultBalance (DataExchange data) throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException 

User can see the balance of his/her card. Returns a message on the screen showing the balances. The user must introduce his/her data. The method checks if the introduced card is registered and the pin is correct.
Returns a CreateSystemException if it is not accomplished.

###### (f) public static String consultMovements (DataExchange data) throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException

User can consult movements. Returns a message on the screen showing the movements. The user must introduce his/her data. The method checks exactly the same conditions as the previous method 'consultBalance'.
If they are not satisfied, returns a CreateSystemException.

###### public static JFormattedTextField prepareAmountInputField (int x1, int y1, int x2, int y2)

As many controls are created in a similar way, this method has been programed to create a JFormattedTextField control for amounts in these coordinates. 

###### public static JPasswordField preparePinField (int x1, int y1, int x2, int y2)

As many controls are created in a similar way, this method has been programed to create a JPasswordField control for amounts in these coordinates. The user must introduce exactly four digits.

#### Other methods

>GenericFunctions.java: contain generic methods:

###### public static JTextPane createOutputField(int x1, int y1, int x2, int y2) 

As many controls are created in a similar way, this method has been programed to create a JTextPane control for send info to the user in these coordinates. 

###### public static JButton createButton(String text, int x1, int y1, int x2, int y2)

As many controls are created in a similar way, this method has been programed to create a JButton control in these coordinates. 

###### public static JLabel createLabel(String text, int x1, int y1)

As many controls are created in a similar way, this method has been programed to create a JLabel control to provide info related to input data fiels in these coordinates. 

###### public static JTextPane errorField(int x1, int y1, int x2, int y2)

As many controls are created in a similar way, this method has been programed to create a JTextPane control for send error messages in these coordinates. 

###### public static JTextField createInputField(int x1, int y1, int x2, int y2)

As many controls are created in a similar way, this method has been programed to create a JTextField control for input data in these coordinates. 

>Card.java : Defines the object card. Contains the name and surname of the User. It has the cardnumber, pin, balance and expirationDate of the User's cards. Creates a new card.

 - To create this system, we have decided to create the object 'Card'. The name and surname of user, the pin, expiration date and balance, are asigned to the Card.

###### public Card(String name, String surname, String cardNumber, String pin, String expirationDate, double balance) throws CreateCardException

New card can be registered. Creates a Card object. Otherwise, it returns an exception when the name or surname is blank, the card number is not valid (must have 12 digits), the pin is not valid too (must have 4 digits),
the expiration date is not like 'mm/dd/yy' or the card has no money.

###### public Card() throws CreateCardException

Creates a Card object.

###### public static String realFormatCardNumer(String cardNumber)

Given a card number, returns the real format of the card, that is twelve zeros plus the card number.

>CardList.java : Defines a card list object. Contains a list with all cards and the method cardNumberInSystem.

- To order the cards, we have decided to have them in a list.

###### public static Card cardNumberInSystem(String cardNumber)

Given a card number, returns the card which has this input.

>CardMovements.java : Defines a card movements object. Contains all the movements of a card and the method lastMovement.

- It is a basic class that is formed by a setter, getter and one method that returns the movements of a list.

###### public static Movement lastMovement() throws JAXBException

Using de method getMovementsList(), returns the last movement of a list. Otherwise, throws an exception.

>DateTimeAdapter.java : turns strings to Date objects and Date objects to strings.

- To have the date in the same format we have implemented the following methods:

###### public Date unmarshal(String xml) throws Exception

Given a string, returns the same content but changed to Date object. If not, returns an exception.

###### public String marshal(Date object) throws Exception

Does the opposite of the previous method. Given a Date object, returns the same content but changed to string. If not, returns an exception.

>List.java : has the setters and getters of all the lists: CardList and MovementsList.

>Movement.java : Defines a movement object. Creates a new movement with the card number, date and amount.

- This is an other important object to complete the system. We created the object 'Movement'. The card number, date and amount to move, are asigned to the Movement.

###### public Movement(String cardNumber, String date, double amount)

New movement can be done by giving the card number, the date and the amount you want to move.

###### public Movement() throws CreateMovementException

Creates a Movement object. Otherwise, returns an exception.


#### Project's exceptions

- CreateCardException()
- CreateMovementException()
- CreateSystemException()
- CreateNumberFormatException()

We used JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException too.

#### Project's tests
 - We have decided to do JUnit tests to prove the methods and interface.

###### System's tests

- CardList test
- CardMovements test
- Card test
- DateTimeAdapter test
- Lists test
- Movement test
- PrepaidSystem test

###### Interface's tests

- BuyCardWindow test
- ChangePinWindow test
- ChargeMoneyWindow test
- ChargePayTicketWindow test
- ConsultBalanceTicketWindow test
- ConsultBalanceWindow test
- ConsultMovementsWindow test
- MainWindow test
- MovementsListWindow test
- BuyCardTicketWindow test
- GenericFunctions test

