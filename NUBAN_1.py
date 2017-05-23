class NUBAN(object):
  """Utility for verifying NUBAN"""
  
  def is_valid_NUBAN(self, bank_code, account_number):
    return is_valid_NUBAN(bank_code.strip() + account_number.strip())

  def is_valid_NUBAN(self, account_number):
    """
    @param account_number A 13-digit bank_code+NUBAN string
    """
    account_number = account_number.strip()

    if len(account_number) != 13:
      return False

    account_number_digits = list(map(int, account_number)) #  Python 2: map(int, account_number)

    sum  = \
      (account_number_digits[0] * 3) + (account_number_digits[1] * 7) + (account_number_digits[2] * 3) +\
      (account_number_digits[3] * 3) + (account_number_digits[4] * 7) + (account_number_digits[5] * 3) +\
      (account_number_digits[6] * 3) + (account_number_digits[7] * 7) + (account_number_digits[8] * 3) +\
      (account_number_digits[9] * 3) + (account_number_digits[10] * 7) + (account_number_digits[11] * 3)

    mod = sum % 10
    check_digit = 0 if mod is 0 else 10 - mod

    return check_digit is account_number_digits[12]
    
