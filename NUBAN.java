public class NUBAN {
  public static boolean isValidNUBAN(String bankCode, String accountNumber) {
    return isValidNUBAN(bankCode.trim() + accountNumber.trim());
  }

  public static boolean isValidNUBAN(String accountNumber) {
    accountNumber = accountNumber.trim();

    if (accountNumber.length() != 13) return false; // 3-digit bank code + 10-digit NUBAN

    int[] accountNumberDigits = accountNumber.chars().map(x -> x - '0').toArray();

    int sum =
        (accountNumberDigits[0] * 3) + (accountNumberDigits[1] * 7) + (accountNumberDigits[2] * 3) +
        (accountNumberDigits[3] * 3) + (accountNumberDigits[4] * 7) + (accountNumberDigits[5] * 3) +
        (accountNumberDigits[6] * 3) + (accountNumberDigits[7] * 7) + (accountNumberDigits[8] * 3) +
        (accountNumberDigits[9] * 3) + (accountNumberDigits[10] * 7) + (accountNumberDigits[11] * 3);

    int mod = sum % 10;
    int checkDigit = (mod == 0) ? mod : 10 - mod;

    return checkDigit == accountNumberDigits[12];
  }
}