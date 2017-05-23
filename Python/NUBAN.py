
def __main__():
    '''Program to validate NUBAN account'''
    print("Hello!")
    code = input("Enter the bank code:_")
    account_number = input("Enter the account number:_")

    if validate_nuban_account(code, account_number):
        response = "Valid"
    else:
        response = "Invalid"

    print("The account number is {}.".format(response))

def validate_nuban_account(bank_code, account_number):
    '''Validate Account Function'''
    result = False

    if len(bank_code.rstrip()) == 3 and len(account_number.rstrip()) == 10:
        nuban = bank_code + account_number[:-1]

        check_digit = int(account_number[9:10])
        nuban_array = []

        for i in range(0, len(nuban)):
            nuban_array.append(int(str(nuban[i])))
        nuban_sum = (nuban_array[0]*3 + nuban_array[1]*7 + nuban_array[2]*3
                     + nuban_array[3]*3 + nuban_array[4]*7 + nuban_array[5]*3
                     + nuban_array[6]*3 + nuban_array[7]*7 + nuban_array[8]*3
                     + nuban_array[9]*3 + nuban_array[10]*7 + nuban_array[11]*3)

        cal_check_digit = 10 - (nuban_sum%10)
        if cal_check_digit == 10:
            cal_check_digit = 0

        if check_digit == cal_check_digit:
            result = True
    return result

if __name__ == '__main__':
    __main__()
