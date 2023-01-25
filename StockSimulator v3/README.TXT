
Student name: Klaidas Predeinas

Student number: 190434953

Skype details: kladias902 | klaidasrange@gmail.com

LEVEL YOU WISH THE MINI-PROJECT TO BE MARKED FOR: 3

Now complete the statements below for each level you wish to be marked. Replace all text in square brackets.

LEVEL ONE

My code demonstrates inheritance in the following way...

I have a superclass called [Stock]

This superclass is extended into at least three subclasses called [IncomeStock, PennyStock, GrowthStock]

For each of the named subclasses complete the following...

Subclass 1.

Subclass [IncomeStock] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [IncomeFactor, successFactor and risk Factor]

These/this new properties/property are used by the subclass in the following way: [These new properties are used to calculate dividends and the change of price based on the type of stock. These properties are used in my updatePrice method (lines: 19-31) and updateDividend method (lines: 55 - 62)]

Subclass [IncomeStock] extends the superclass by overriding the following methods (there must be at least one): [updateDividend(line 55) and updatePrice(line 19) are overridden in my subclass]

These overridden methods are used in the working code in the following places: [StockMarketFrame.java (line 113) update price is called, it updates the price of all 3 subclasses, StockMarketFrame.java (line 196) updateDividend class is called, this is done on all 3 subclasses, the same method is called on line 237]

Subclass 2.

Subclass [PennyStock] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [rewardFactor, successFactor and riskFactor]

These/this new properties/property are used by the subclass in the following way: [These new properties are used to caclulate the dividends and new price of the stock, rewardFactor is added on, because the user has a high chance of losing money by investing. These properties are used in my updatePrice method (lines: 20-32) and updateDividend method (lines: 60 - 62)]

Subclass [PennyStock] extends the superclass by overriding the following methods (there must be at least one): [updateDividend(line 60) and updatePrice(line 20) are overridden in my subclass]

These overridden methods are used in the working code in the following places: [StockMarketFrame.java (line 113) update price is called, it updates the price of all 3 subclasses, StockMarketFrame.java (line 196) updateDividend class is called, this is done on all 3 subclasses, the same method is called on line 237]

Subclass 3.

Subclass [GrowthStock] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [growthFactor, successFactor and riskFactor]

These/this new properties/property are used by the subclass in the following way: [These properties were added for all the subclasses as the priceChange and dividends depends on the stock/company, so based on the stock you have difference chances of getting more or less money from the stock. These properties are used in my updatePrice method (lines: 20-32) and updateDividend method (lines: 56 - 63)]

Subclass [GrowthStock] extends the superclass by overriding the following methods (there must be at least one): [updateDividend(line 56) and updatePrice(line 20) are overridden in my subclass]

These overridden methods are used in the working code in the following places: [StockMarketFrame.java (line 113) update price is called, it updates the price of all 3 subclasses, StockMarketFrame.java (line 196) updateDividend class is called, this is done on all 3 subclasses, the same method is called on line 237]

LEVEL TWO

polymorphism is a concepted used in OOP (Object oriented programming), It has two main principles to it. Dynamic binding and substitution principle, substitution as the name suggest it replaces something, in OOP subclasses replace methods in the super class (by having the same name and parameters). Whereas dynamic binding is when the code doesn't know what something will do until it is run in the program. 

[GrowthStock.java (line 20-32, 56-63), PennyStock.java (line 20-32, 60-62), IncomeStock.java (line 19-31, 55-62) are examples of where substitution principle is used. Dynamic binding is used frequently, some examples of this are: StockMarketFrame.java line numbers ( 112-114, 122-124, (185-248 more specific location: line 196, 237), 276). It is also used in my StockPortfolio.java as it takes in "Stock" which can be any one of the subclasses and it would still work]

[Polymorphism is necessary in my code, because some methods like updatePrice and updateDividend are different in different types of stock, without polymorphism, I would have to do sane calculation for all types of stocks. But it allows me to change methods. Further, it allows me to create more generic functions which work for all types of stocks instead of making the same method 3 times.]

LEVEL THREE

[I have a few buttons on my GUI, one which allows you to buy stock from the market, when you press the button, it gets the name of the button which is specific to each stock, then removes from the market and adds to the user portfolio and takes some money away from the user. this is done in StockMarketFrame.java on the line numbers: 185 - 218. Then I have button which creates a new account, which simply sets market and account to preset values, line numbers (66-73, 290 - 302)]

[One example of exception handling is done when reading the file or writing the file. I use try and catch to make sure the user doesn't get an error but instead a console log. IFile lines: (57-71, 73-87), Another example of this is when you try to remove stock (sell stock) and you don’t have enough, I've made a custom error which I catch in my GUI interface which will then append a message to the console saying you don't have enough. StockMarketFrame.java (line 226-230)]

[StockPortfolio.java uses Map, hashmap line: 5, Also used in my CLI implementation line: 66]

[I've created a format which the file is written to, which is done when the user presses save and exit, if it’s not pressed it will not save. Then if the user chooses to load an account it will read the specific format I've made via split and then return the class version of the state of the simulation which are the market and the account.]

ANYTHING ELSE

[To run my program you run main.java, this will give you the option to either go CLI or GUI. If you just want to run GUI, run the StockMarketFrame.java file instead]
