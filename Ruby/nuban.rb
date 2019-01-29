# A class that check if NUBAN is valid per CBN rules
class Nuban
  MULTIPLICANTS = [3, 7, 3].cycle

  attr_reader :number, :bank_code

  def initialize(number, bank_code)
    raise ArgumentError unless /\d{10}/.match?(number) && /\d{3}/.match?(bank_code)

    @number = number
    @bank_code = bank_code
  end

  def valid?
    check_digit.eql?(calculate_check_digit)
  end

  private

  def calculate_check_digit
    step_one =
      (bank_code + serial)
      .split('')
      .zip(MULTIPLICANTS)
      .map { |num, mult| num.to_i * mult }
      .sum

    step_two = step_one % 10
    step_three = 10 - step_two

    step_three % 10
  end

  def serial
    number[0..-2]
  end

  def check_digit
    number[-1].to_i
  end
end

p Nuban.new('0000014579', '011').valid?
