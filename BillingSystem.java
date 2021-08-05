import java.util.Scanner;
import java.util.ArrayList;

    class CafeteriaItems
    {
        private String item_Name;
        private double item_Price;  
        private int qty;  

        public CafeteriaItems(String itemName,double itemPrice,int quantity)
        {
            this.item_Name = itemName;
            this.item_Price = itemPrice;
            this.qty = quantity;
        }

        public double getItemPrice()
        {
            return item_Price;
        }

        public void setQty(int quantity)
        {
            this.qty = quantity;
        }

        public int getQty()
        {
            return qty;
        }
        public String getItemName()
        {
            return item_Name;
        }
    }

public class BillingSystem {
    public static void main(String[]args)
    {
       
        ArrayList<CafeteriaItems> cafeItems = new ArrayList<CafeteriaItems>();

        CafeteriaItems water = new CafeteriaItems("Water", 0.50, 1);
        CafeteriaItems sandwich = new CafeteriaItems("Sandwich", 1.50, 1);
        CafeteriaItems coffee = new CafeteriaItems("Coffee", 1.50, 1);
        CafeteriaItems soda = new CafeteriaItems("Coke", 1, 1);
        CafeteriaItems croissant = new CafeteriaItems("Croissant", 3, 1);
        CafeteriaItems pizzaSlice = new CafeteriaItems("Pizza Slice", 3.50, 1);
        CafeteriaItems cake = new CafeteriaItems("Cake", 4, 1);
        CafeteriaItems bun = new CafeteriaItems("Bun", 4.50, 1);
        CafeteriaItems salad = new CafeteriaItems("Salad", 5.30, 1);
        CafeteriaItems pasta = new CafeteriaItems("Pasta", 6.70, 1);
    

        cafeItems.add(water);
        cafeItems.add(cake);
        cafeItems.add(coffee);
        cafeItems.add(soda);
        cafeItems.add(pasta);
        cafeItems.add(croissant);
        cafeItems.add(salad);
        cafeItems.add(bun);
        cafeItems.add(sandwich);
        cafeItems.add(pizzaSlice);
        
        double tax = 0.0675;  
        double tip = 0.1;
        double m_tip;
        double sum = 0.00;

       
        ArrayList<CafeteriaItems> boughItems = new ArrayList<CafeteriaItems>();
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Welcome to the Cafeteria!");
        while(true)
        {
            displayMenu(cafeItems);
            int selection = kb.nextInt();
            if(selection==0)
            {
                break;
            }
            else
            {
                System.out.println("You have chosen: "+cafeItems.get(selection-1).getItemName()+" Price:"+cafeItems.get(selection-1).getItemPrice());
                System.out.println("Please enter the quantity: ");
                int quan = kb.nextInt();
                addItems(boughItems, cafeItems.get(selection-1), quan);
                System.out.println("Item added!");
            }
            
        }   

        sum = calculateSum(boughItems, sum);
        
        if(sum==0)
        {
            System.out.println("Thank you for browsing, please come again");
        }
        else
        {
            System.out.println("\tGive more tips? $:__  (press 0 for no)");
            m_tip = kb.nextDouble();
            if(m_tip<=0)
            {
                displayFinalBill(boughItems,m_tip,sum,tax,tip);
            }
            else 
            {
                System.out.println("Thank you, you have tipped an extra: $"+m_tip);
                displayFinalBill(boughItems, m_tip, sum, tax, tip);
            }
        }
    }

    static void displayFinalBill(ArrayList<CafeteriaItems> bought,double m_tip,double sum,double tax,double tip)
    {
        System.out.println("Thank you for patronising us!\n");
        System.out.println("Bill: \n");
        for(int i=0;i<bought.size();i++)
        {
            System.out.println("Item: "+bought.get(i).getItemName()+" Price:"+bought.get(i).getItemPrice()+" Qty:"+bought.get(i).getQty()+" PRICE: "+
            bought.get(i).getItemPrice()*bought.get(i).getQty()+"\n");
        }

        System.out.println("Tax(6.75%) = "+Math.round((sum*tax)*100.0)/100.0); 
        double tolTax = Math.round((sum*tax)*100.0)/100.0;    
        double afterTax = sum + tolTax;  // after tax is added
        System.out.println("Tips: $"+Math.round((afterTax*tip)*100.0)/100.0+" Extra tips: $"+m_tip);
        double totalTips = m_tip + Math.round((afterTax*tip)*100.0)/100.0;// total amount of tips 
        double totalSum = Math.round((afterTax+totalTips)*100.0)/100.0;
        System.out.println("After tax:$"+afterTax+" + Total tips:$"+totalTips+" Total Sum = $"+totalSum);

    }

    static void addItems(ArrayList<CafeteriaItems> bought,CafeteriaItems sltItems,int qty)
    {
        sltItems.setQty(qty);
        bought.add(sltItems);
    }

    static double calculateSum(ArrayList<CafeteriaItems> bought,double sum)
    {
       for(int i=0;i<bought.size();i++)
       {
           sum = sum + bought.get(i).getItemPrice()*bought.get(i).getQty();
       }
        
       return sum;
    }


     static void displayMenu(ArrayList<CafeteriaItems> cafemenuArrayList)
    {
        int k=0;
        System.out.println("Please choose an item: Press 0 to end\n");
        for(int i=0;i<cafemenuArrayList.size();i++)
        {
            System.out.print(i+1+". "+cafemenuArrayList.get(i).getItemName()+" $"+cafemenuArrayList.get(i).getItemPrice()+"\t");
            k++;
            if(k%3==0)
            {
                System.out.println();
            }
        }
    }
}
