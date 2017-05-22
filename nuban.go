package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
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
	var accNum string

	bankCode := getInput("Bank Code", 3)
	bank := getBank(bankCode)

	accNum = bankCode + getInput("Account Number", 10)
	isValid := validateAccNum(accNum)

	fmt.Printf("\n\nAccount Number: %v \nBank: %v \nValid: %v\n", accNum, bank, isValid)
}

func getInput(info string, maxLength int) string {
	fmt.Printf("Enter %v: ", info)
	reader := bufio.NewReader(os.Stdin)
	message, readErr := reader.ReadString('\n')
	if readErr != nil {
		log.Fatal("Error: ", readErr)
	}

	message = strings.TrimSpace(message)
	_, intErr := strconv.Atoi(message)
	if intErr != nil {
		log.Fatal("Error: Must be a number")
	}

	if len(message) != maxLength {
		log.Fatalf("Error: %v must be %v digits", info, maxLength)
	}

	return message
}

func getBank(bankCode string) string {
	bank, ok := banks[bankCode]
	if !ok {
		log.Fatal("Invalid Bank Code")
	}
	return bank
}

func validateAccNum(accNum string) bool {
	var checkDigit int
	var calcCheckDigit int

	for i := 0; i < 12; i++ {
		char, _ := strconv.Atoi(string(accNum[i]))
		calcCheckDigit += (char * algo[i])
	}

	checkDigit, _ = strconv.Atoi(string(accNum[12]))
	calcCheckDigit = (10 - (calcCheckDigit % 10)) % 10

	return calcCheckDigit == checkDigit
}
