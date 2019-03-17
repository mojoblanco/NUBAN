/// NUBAN verification algorithm
///
/// How to Use:
///
/// Once you have successfuly installed rust
/// run the following commands
///
/// ```bash
///     rustc NUBAN.rs
/// ```
/// Then run
///
/// ```bash
///     ./NUBAN BANK_CODE ACCONT_NUMBER
/// ```
///
/// Replace `BANK_CODE` and `ACCOUNT_NUMBER` with real numbers
///


use std::process;
use std::env;

fn main() {
    let args: Vec<String> = env::args().collect();
    if let Ok(true) = is_valid(&args) {
        println!("{}", true);
        process::exit(0);
    } else {
        println!("{}", false);
        process::exit(1);
    }
}

fn is_valid(args: &[String]) -> Result<bool, bool> {
    if args.len() < 3 { panic!("Invalid arguments, please check your input"); }

    let bank_code = args[1].clone();
    let mut account_number = args[2].clone();

    if bank_code.len() != 3 || account_number.len() != 10 {
        panic!("Invalid arguments, please check your input");
    }

    let check_digit = account_number.pop().unwrap();
    let mut numbers = String::new();
    numbers.push_str(&bank_code);
    numbers.push_str(&account_number);
    let number_ints = numbers.chars().map(|num| num.to_digit(10).unwrap());

    let multipliers = vec![3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3];
    let mut check_sum = 0;
    for (idx, num) in number_ints.enumerate() {
        check_sum += num * multipliers[idx];
    }

    let mut r_check_digit = 10 - (check_sum % 10);
    if r_check_digit == 10 { r_check_digit = 0; }

    if r_check_digit == check_digit.to_digit(10).unwrap() {
        Ok(true)
    } else {
        Err(false)
    }
}