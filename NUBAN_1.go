package main

import (
	"bufio"
    "fmt"
    "os"
    "strings"
    "strconv"
    "log"
)

var banks = map[string]string{
	"044": "Access Bank",
	"014": "Afribank",
	"023": "Citibank",
	"063": "Diamond Bank",
	"050": "Ecobank",
	"040": "Equitorial Trust Bank",
	"011": "First Bank",
	"214": "FCMB",
	"070": "FinBank",
	"058": "Guarantee Trust Bank",
	"069": "Intercontinentl Bank",
	"056": "Oceanic Bank",
	"082": "BankPhb",
	"076": "Skye Bank",
	"084": "SpringBank",
	"221": "StanbicIBTC",
	"068": "Standard Chartered Bank",
	"232": "Sterling Bank",
	"033": "United Bank For Africa",
	"032": "Union Bank",
	"035": "Wema Bank",
	"057": "Zenith Bank",
	"215": "Unity Bank",
}

var algo = [12]int{3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3}

func main() {
	fmt.Println("Hello there")
	fmt.Println("Enter the bank sort code")
	reader := bufio.NewReader(os.Stdin)
	sortcode, _ := reader.ReadString('\n')
	fmt.Println(sortcode)
	fmt.Println("Enter the account number")
	account_number, _ := reader.ReadString('\n')
	fmt.Println(account_number)
	response := ""
	response_bool := validate_nuban_account(sortcode, account_number)
	if(response_bool == true){
		response = "The account number is Valid"
	}else{
		response = "The account number is Invalid"
	}
    println(response)
}

func validate_nuban_account(bankCode string, accountNumber string) bool {
       result := false

        if (len(strings.TrimSpace(bankCode)) == 3 && len(strings.TrimSpace(accountNumber)) == 10){
            nuban := bankCode + accountNumber[:8]
            checkDigit, err := strconv.Atoi(accountNumber[:8])
		    if err != nil {
		        // account number cannot be parsed to integer
		        log.Fatal("Error: must be a number")
		    }
		    nubanArray := make([]int, len(nuban))
		    for i := 0; i < len(nuban); i++ {
		    	str := convert(nuban[i])
		    	nubanArray[i], err = strconv.Atoi(str)
		    }
		    nubanSum := 0

            for i := 0; i < 12; i++ {
				nubanSum += (nubanArray[i] * algo[i])
			}
            calCheckDigit := 10 - (nubanSum % 10)

            if(checkDigit != 10){
            	 calCheckDigit = calCheckDigit
            }else{
            	calCheckDigit = 0
            }
            result = (checkDigit == calCheckDigit)
        }

   	return result

}

func convert( b byte ) string {   
    s := strconv.Itoa(int(b))
    return s
}
