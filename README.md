Data Miner
============

Data Miner gathering information in txt file with shopping data.

Example
-------------------------

**Arguments**

In order to run program, user has to pass following arguments:

- -f, path to file with data
- -t, value of support threshold
- -p, number of products

**Input**

Input must be save in *.txt file with such a format.

    "2765";"964"
    "19287";"964"
    "20006";"964"
    "20185";"964"
    "5193";"964"
    "145";"964"
    "20984";"964"
    "21164";"964"
    "21264";"964"

**Output**

Output is displayed in such a format.

    [3557, 3576, 3577, 3581, 3817] = 7
    [3557, 3575, 3576, 3577, 3817] = 11
    [3556, 3575, 3576, 3577, 3817] = 9
    [3558, 3561, 3571, 3574, 3577] = 7
    [3556, 3557, 3575, 3576, 3577] = 9
    [3556, 3557, 3575, 3576, 3817] = 7
    [3556, 3557, 3576, 3577, 3817] = 9
    [3557, 3558, 3576, 3577, 3817] = 10
    [3556, 3557, 3575, 3577, 3817] = 11