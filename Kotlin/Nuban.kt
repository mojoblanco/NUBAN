import java.util.*

class Nuban {

    val banks = mapOf (
            "044" to "Access Bank",
            "014" to "Afribank",
            "023" to "Citibank",
            "063" to "Diamond Bank",
            "050" to "Ecobank",
            "040" to "Equitorial Trust Bank",
            "011" to "First Bank",
            "214" to "FCMB",
            "070" to "FinBank",
            "058" to "Guarantee Trust Bank",
            "069" to "Intercontinental Bank",
            "056" to "Oceanic Bank",
            "082" to "BankPhb",
            "076" to "Skye Bank",
            "084" to "SpringBank",
            "221" to "StanbicIBTC",
            "068" to "Standard Chartered Bank",
            "033" to "United Bank For Africa",
            "032" to "Union Bank",
            "035" to "Wema Bank",
            "057" to "Zenith Bank",
            "215" to "Unity Bank" )

    val algo = intArrayOf(3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3)


    fun validateNubanAccount(bankCode: String, accountNumber: String): Boolean {

        if (bankCode.length != 3 || accountNumber.length != 10) {
            return false
        }

        val nubanSerialNo = accountNumber.substring(0, accountNumber.length - 1)
        val nubanAccNo = bankCode + nubanSerialNo

        val checkDigit = Character.getNumericValue(accountNumber[accountNumber.length - 1])

        var sum = 0
        for (j in 0..nubanAccNo.length - 1) {
            sum += Character.getNumericValue(nubanAccNo[j]) * algo[j]
        }

        val validatedCheckDigit = 10 - (sum % 10)

        return checkDigit == (validatedCheckDigit % 10);

    }
}


fun main(args : Array<String>) {

    println("Validate Nuban Account")
    println("----------------------")

    val input1 = Scanner(System.`in`)
    println("Enter Account Number: ")
    val accountNumber = input1.next()

    val input2 = Scanner(System.`in`)
    println("Enter Bank Code: ")
    val bankCode = input2.next()


    val response = Nuban().validateNubanAccount(bankCode, accountNumber)
    if(response == true){
        print("The account number is Valid")
    } else {
        print("The account number is Invalid")
    }


}
