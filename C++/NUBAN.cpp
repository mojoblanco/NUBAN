#include <iostream>
#include <map>

using namespace std;

int algo[12] = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};
map<string, string> banks = {
    {"044", "Access Bank"},
    {"014", "Afribank"},
    {"023", "Citibank"},
    {"063", "Diamond Bank"},
    {"050", "Ecobank"},
    {"040", "Equitorial Trust Bank"},
    {"011", "First Bank"},
    {"214", "FCMB"},
    {"070", "FinBank"},
    {"058", "Guarantee Trust Bank"},
    {"069", "Intercontinentl Bank"},
    {"056", "Oceanic Bank"},
    {"082", "BankPhb"},
    {"076", "Skye Bank"},
    {"084", "SpringBank"},
    {"221", "StanbicIBTC"},
    {"068", "Standard Chartered Bank"},
    {"232", "Sterling Bank"},
    {"033", "United Bank For Africa"},
    {"032", "Union Bank"},
    {"035", "Wema Bank"},
    {"057", "Zenith Bank"},
    {"215", "Unity Bank"},
};

tuple<string, string>
getInput()
{
  string bankCode, accountNumber;
  cout << "Enter the bank code: ";
  cin >> bankCode;
  cout << "Enter the account number: ";
  cin >> accountNumber;

  return make_tuple(bankCode, accountNumber);
}

bool validateNubanAccount(string bankCode, string accountNumber)
{
  if (bankCode.size() != 3 || accountNumber.size() != 10)
    return false;

  char checkDigit = accountNumber.back() - '0';
  accountNumber.pop_back();
  string nuban = bankCode + accountNumber;

  string::iterator it;
  int idx = 0;
  int acc = 0;
  for (it = nuban.begin(); it < nuban.end(); ++it, ++idx)
    acc += (*it - '0') * algo[idx];

  return checkDigit == (10 - (acc % 10)) % 10;
}

int main()
{
  string bankCode, accountNumber;
  tie(bankCode, accountNumber) = getInput();
  string valid = validateNubanAccount(bankCode, accountNumber) ? "Yes" : "No";

  cout << endl
       << "Account Number: " << accountNumber << endl
       << "Bank Code: " << bankCode << endl
       << "Bank: " << banks[bankCode] << endl
       << "Valid: " << valid
       << endl;
}
