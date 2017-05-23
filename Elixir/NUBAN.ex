defmodule NUBAN do

  def is_valid_NUBAN(bank_code, account_number) do
    is_valid_NUBAN(String.trim(bank_code) <> String.trim(account_number))
  end
  
  def is_valid_NUBAN(account_number) do
    case String.length(account_number) do
      13 -> do_is_valid_NUBAN(account_number)
      _ -> false
    end
  end

  defp do_is_valid_NUBAN(account_number) do
    [t1, s1, t2, t3, s2, t4, t5, s3, t6, t7, s4, t8, check_digit] =
      account_number
      |> String.split("", trim: true)
      |> Enum.map(fn x ->
       {num, _} = Integer.parse(x)
       num
       end)

    sum =
      (t1 * 3) + (s1 * 7) + (t2 * 3) +
      (t3 * 3) + (s2 * 7) + (t4 * 3) +
      (t5 * 3) + (s3 * 7) + (t6 * 3) +
      (t7 * 3) + (s4 * 7) + (t8 * 3)

    case rem(sum, 10) do
      x when (10 - check_digit) == x -> true
      0 -> check_digit == 0
      _ -> false
    end
  end
end