/**
 * Created by saopayne on 5/23/17.
 */
class NUBAN {

    static void main(String [] args)
    {
        println("Hello");

        println("Enter the bank code")
        Scanner input = new Scanner(System.in)
        String code = input.nextLine()
        println ("Enter the account number")
        String accountNumber = input.nextLine()

        String response = ValidateNubanAccount(code, accountNumber) ? "Valid" : "Invalid"

        println("The account number is ${response}")
    }

    public static boolean ValidateNubanAccount(String bankCode, String accountNumber)
    {
        def result = false;

        if (bankCode.trim().length() == 3 && accountNumber.trim().length() == 10)
        {
            def nuban = bankCode + accountNumber.substring(0, 9)
            def checkDigit = Integer.parseInt(accountNumber.substring(0, 9))
            def nubanArray = new int[nuban.length()]
            for (int i = 0; i < nuban.length(); i++)
            {
                nubanArray[i] = Integer.parseInt(nuban[i].toString())
            }
            println(nubanArray.length)
            def nubanSum = (nubanArray[0] * 3) + (nubanArray[1] * 7) + (nubanArray[2] * 3) + (nubanArray[3] * 3) +
                    (nubanArray[4] * 7) + (nubanArray[5] * 3) + (nubanArray[6] * 3) + (nubanArray[7] * 7) +
                    (nubanArray[8] * 3) + (nubanArray[9] * 3) + (nubanArray[10] * 7) + (nubanArray[11] * 3)
            def calCheckDigit = 10 - (nubanSum % 10)
            calCheckDigit = calCheckDigit != 10 ? calCheckDigit : 0
            result = (checkDigit == calCheckDigit)
        }

        return result
    }

}
