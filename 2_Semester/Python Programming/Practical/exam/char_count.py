def char_count(data: str) -> None:
    data_increase_order = {}
    for ch in data:
        if ch not in data_increase_order.keys() and ch != " ":
            data_increase_order[ch] = data.count(ch)

    # sorter_char = sorted(data_increase_order.items(), key=lambda x: x[1])
    # for char, counter in sorter_char:
    #     print(char, ":", counter)

    for char in data_increase_order.items():
        print(char[0], ":", char[1])


char_count("data formatted")
