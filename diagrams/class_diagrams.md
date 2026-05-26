## Orders

```mermaid
classDiagram
    class Order {
        -List&lt;LineItem&gt; items
        +double calculateTotal()
        +void addItem(LineItem item)
    }
    class LineItem {
        <<interface>>
        +double getPrice()
    }

    class Size {
        -int id
        -String name
        -String measurement
    }

    LineItem <|-- Sandwich
    class Sandwich {
        -Size size;
        -List&lt;Addition&gt; additions
        -boolean toasted
        -@Nullable String name
    }
    class Addition {
        -String name
        -String category
        -double[] pricePerSizeClass
        -boolean premium
        +computePrice(int size)
    }
    
    LineItem <|-- Drink
    class Drink {
        -int size
    }
    LineItem <|-- Chips
    class Chips {
        
    }
```

## Data Files
```mermaid
classDiagram
    class ReceiptWriter {
        -String receiptsDirectory
        +void writeOrder(LocalDate moment, Order order)
    }
    class SandwichDataReader {
        +List&lt;Size&gt; getSizes()
        
        +List&lt;Addition&gt; getAdditions()
    }
```

### Private Data Reader Classes
The following classes are not usable outside of `com.pluralsight.data`.
They are relevant only to the internals around reading and writing
the files we're working with.
```mermaid
classDiagram
    class SandwichAdditionCategory {
        -int id
        -String name
        -List&lt;Double&gt; pricesBySize
        -@Nullable List&lt;Double&gt; extraPricesBySize
    }
    class SandwichAddition {
        -int id
        -int categoryId
        -String name
    }
```

## UI
```mermaid
classDiagram
    class UserInterface
    class Screen {
        <<interface>>
        Screen run(Scanner userInput)
    }
    
    Screen <|-- HomeScreen
    class HomeScreen
    Screen <|-- OrderScreen
    class OrderScreen {
        -Order order
    }
    Screen <|-- AddSandwichScreen
    class AddSandwichScreen {
        -Order order
    }
    Screen <|-- AddDrinkScreen
    class AddDrinkScreen {
        -Order order
    }
    Screen <|-- AddChipsScreen
    class AddChipsScreen {
        -Order order
    }
    Screen <|-- CheckoutScreen
    class CheckoutScreen {
        -Order order
    }
```

For fun, we're structuring the UI flow around classes whose `run()` method
returns the next screen to enter.
If it is `null`, the UI exits.
