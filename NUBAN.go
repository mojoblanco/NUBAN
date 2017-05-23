package main

import (
	"bufio"
    "fmt"
    "os"
    "strings"
    "strconv"
)

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
		        fmt.Println(err)
		        os.Exit(2)
		    }
		    nubanArray := make([]int, len(nuban))
		    for i := 0; i < len(nuban); i++ {
		    	str := convert(nuban[i])
		    	nubanArray[i], err = strconv.Atoi(str)
		    }
            fmt.Println(len(nubanArray))
            nubanSum := (nubanArray[0] * 3) + (nubanArray[1] * 7) + (nubanArray[2] * 3) + (nubanArray[3] * 3) +
                    (nubanArray[4] * 7) + (nubanArray[5] * 3) + (nubanArray[6] * 3) + (nubanArray[7] * 7) +
                    (nubanArray[8] * 3) + (nubanArray[9] * 3) + (nubanArray[10] * 7) + (nubanArray[11] * 3)
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