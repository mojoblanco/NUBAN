using System;

namespace NUBAN_Validator
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello");

            Console.WriteLine("Enter the bank code");
            string code = Console.ReadLine();
            Console.WriteLine("Enter the account number");
            string accountNumber = Console.ReadLine();

            string response = ValidateNubanAccount(code, accountNumber) ? "Valid" : "Invalid";

            Console.WriteLine($"The account number is {response}");
        }

        public static bool ValidateNubanAccount(string bankCode, string accountNumber)
        {
            var result = false;
            try
            {
                if (bankCode.Trim().Length == 3 && accountNumber.Trim().Length == 10)
                {
                    var nuban = bankCode + accountNumber.Remove(9, 1);
                    var checkDigit = Convert.ToInt32(accountNumber.Substring(9, 1));
                    var nubanArray = new int[nuban.Length];
                    for (int i = 0; i < nuban.Length; i++)
                    {
                        nubanArray[i] = Convert.ToInt32(nuban[i].ToString());
                    }
                    var nubanSum = (nubanArray[0] * 3) + (nubanArray[1] * 7) + (nubanArray[2] * 3) + (nubanArray[3] * 3) +
                                   (nubanArray[4] * 7) + (nubanArray[5] * 3) + (nubanArray[6] * 3) + (nubanArray[7] * 7) +
                                   (nubanArray[8] * 3) + (nubanArray[9] * 3) + (nubanArray[10] * 7) + (nubanArray[11] * 3);
                    var calCheckDigit = 10 - (nubanSum % 10);
                    calCheckDigit = calCheckDigit != 10 ? calCheckDigit : 0;
                    result = (checkDigit == calCheckDigit);
                }
            }
            catch { }
            return result;
        }
    }
}
