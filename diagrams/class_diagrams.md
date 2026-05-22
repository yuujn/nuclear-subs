## Orders

```mermaid
classDiagram
    class Order {
        -List&lt;LineItem&gt; items
        +double calculateTotal()
    }
    class LineItem {
        <<interface>>
        +double getPrice()
    }
    
    LineItem <|-- Sandwich
    class Sandwich {
        -List&lt;Addition&gt; addition
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
```

## UI
```mermaid
classDiagram
    class UserInterface
    class Screen {
        <<interface>>
        Screen run()
    }
    
    Screen <|-- HomeScreen
    class HomeScreen
    Screen <|-- OrderScreen
    class OrderScreen
    Screen <|-- AddSandwichScreen
    class AddSandwichScreen
    Screen <|-- AddDrinkScreen
    class AddDrinkScreen
    Screen <|-- AddChipsScreen
    class AddChipsScreen
    Screen <|-- CheckoutScreen
    class CheckoutScreen
```

For fun, we're structuring the UI flow around classes whose `run()` method
returns the next screen to enter.
If it is `null`, the UI exits.
