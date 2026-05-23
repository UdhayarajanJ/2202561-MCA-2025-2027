def cap_first_all_string(data: str) -> None:

    # result = data.title()

    # text_split = data.split()
    # result = ""
    # for word in text_split:
    #     result += word[0].upper() + word[1:] + " "

    # result = " ".join(word[0].upper() + word[1:] for word in data.split())

    result = data.replace(" ", "-")
    # captilize_method = data.split()
    # for cap in captilize_method:
    #     result += cap.capitalize() + " "

    print(result)


data_input = input("Enter a string: ")
cap_first_all_string(data_input)
