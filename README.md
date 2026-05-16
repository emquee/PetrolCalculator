# Smart Petrol Cost Calculator with BUDI MADANI Rebate

A mobile application developed for the **Mobile Technology Assignment** to estimate total petrol costs in Malaysia and apply the BUDI MADANI fuel subsidy to eligible users, calculating the final payable amount and total savings.

---

## Application Overview

This Android application allows Malaysian users to calculate their petrol expenses based on fuel type, price per litre, and usage amount. It also applies the BUDI MADANI government fuel subsidy rebate for eligible RON95 users.

---

## Features

- Select petrol type: RON95, RON97, or Diesel
- Enter petrol price per litre (RM)
- Enter fuel usage in litres
- Select BUDI MADANI eligibility status (Yes / No)
- Calculates:
  - Total petrol cost
  - BUDI MADANI rebate (RON95 eligible users only)
  - Total saving
- Auto-scrolls to result after calculation
- Navigation menu with Home and About pages
- About page with app icon, author info, copyright notice, and clickable GitHub link

---

## Sample Calculation

| Input | Value |
|---|---|
| Petrol type | RON95 |
| Petrol price per litre | RM 4.27 |
| Fuel usage | 40 litres |
| BUDI MADANI eligibility | Yes |

**Step 1 — Total petrol cost:**
```
Total petrol cost = fuel usage × petrol price per litre
                 = 40 × RM 4.27
                 = RM 170.80
```

**Step 2 — BUDI rebate:**
```
BUDI rebate = fuel usage × subsidy rate (RM 1.99/l)
            = 40 × RM 1.99
            = RM 79.60
```

**Step 3 — Total saving:**
```
Total saving = Total petrol cost − BUDI rebate
             = RM 170.80 − RM 79.60
             = RM 91.20
```

> Rebate applies to **RON95 only**. RON97 and Diesel users receive no rebate regardless of eligibility status.

---

## User Interface

- **Application title** visible on the home screen header
- **Bottom navigation menu** with:
  - Home (Calculator)
  - About
- **About page** includes:
  - Application icon
  - Author name, Matric No, and Course
  - Copyright notice
  - Clickable GitHub repository URL



