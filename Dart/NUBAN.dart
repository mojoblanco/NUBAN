class NUBAN {
  Map<String, String> banks = {
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
  };

  List<int> algo = [3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3];

  bool isValidNUBAN(String bankCode, String accountNumber) {
    bankCode = bankCode.trim();
    accountNumber = accountNumber.trim();
    if (bankCode.length != 3 || accountNumber.length != 10)
      return false; // 3-digit bank code + 10-digit NUBAN

    String fullAccountNumber = bankCode + accountNumber;

    List<int> acctNumbers = fullAccountNumber.codeUnits.map((unit) {
      return unit - '0'.codeUnitAt(0);
    }).toList();

    int sum = 0;
    for (int i = 0; i < acctNumbers.length - 1; i++) {
      sum += acctNumbers[i] * algo[i];
    }
    int mod = sum % 10;
    int checkDigit = (mod == 0) ? mod : 10 - mod;

    return checkDigit == acctNumbers[12];
  }
}
