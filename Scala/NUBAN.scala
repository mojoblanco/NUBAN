/**
Regards to the folks that implemented it in the following language


***Java
***C#
***Python
***Go

*/

import java.lang.Integer;

object NUBAN{

    def main(args: Array[String]){
        println("Enter The Bank Code");
        val bankCode=readLine();

        val response=bankCheck(bankCode);
        if(!response){
            println("That is an invalid bank code");
            return;
        }
        
        println("Enter The Account Number");
        val accountNumber=readLine();

        val result=checkNuban(bankCode.trim(),accountNumber.trim());
        println(result);
    }

    def checkNuban(bankCode: String="",accountNumber: String=""): String={
        try{
            //check the length of the bank code
            if(bankCode.length()>3 || bankCode.length<3){
                return "Invalid Bank Code";
            }

            //check the length of the account number
            if(accountNumber.length>10 || accountNumber.length<10){
                return "Invalid Account Number"
            }

            //this just checks if the value provided is digit
            val int_bankCode=Integer.parseInt(bankCode);
            val int_accountNumber=Integer.parseInt(accountNumber);

            //get the stripped account number
            val strippedAcctNumber=accountNumber.substring(0,9);
            var checkDigit: Int=Integer.parseInt(accountNumber(9).toString());

            // concatenate the account number with the bank code
            val joinedNumber=bankCode+strippedAcctNumber;

            //the array values
            val checkArray=Array(3,7,3,3,7,3,3,7,3,3,7,3);
        
            var totalSum=0;     //this is the total sum of all the items
            var counter=0;
            for(i<-joinedNumber){           //loop through the joined number
                var item=Integer.parseInt(i.toString());           //assing the value of this string character to item
                var arr_item=checkArray(counter);               //assign the value of the array to arr_item
                var product=arr_item*item;

                //add the product of the current operation to the total sum
                totalSum+=product;    
        
                counter=counter+1;         //increment the counter
            }

            val remainder=totalSum%10;
            val remainder_sub=10-remainder;

            if(remainder_sub==10) checkDigit=0 else checkDigit

            if(remainder_sub!=checkDigit) return "Invalid NUBAN" else return "Valid NUBAN";
        }
        catch{
            case e: NumberFormatException => return "Number Format Error... Not a number";
            case _: Throwable => return "Other Error Occurrs";
        }
    }


    def bankCheck(bankCode: String): Boolean={
        var response=false;
        val banks: Map[String,String]=Map(
            "044"-> "Access Bank",
            "014"-> "Afribank",
            "023"-> "Citibank",
            "063"-> "Diamond Bank",
            "050"-> "Ecobank",
            "040"-> "Equitorial Trust Bank",
            "011"-> "First Bank",
            "214"-> "FCMB",
            "070"-> "FinBank",
            "058"-> "Guarantee Trust Bank",
            "069"-> "Intercontinentl Bank",
            "056"-> "Oceanic Bank",
            "082"-> "BankPhb",
            "076"-> "Skye Bank",
            "084"-> "SpringBank",
            "221"-> "StanbicIBTC",
            "068"-> "Standard Chartered Bank",
            "232"-> "Sterling Bank",
            "033"-> "United Bank For Africa",
            "032"-> "Union Bank",
            "035"->"Wema Bank",
            "057"-> "Zenith Bank",
            "215"-> "Unity Bank"
        );

        if(banks.contains(bankCode)) response=true; else response=false
        return response;
    }
    
}