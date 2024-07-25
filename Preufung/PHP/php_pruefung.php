<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Kunde und Bestellngen</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Online Shop</h1>
    <p>Liste von den Bestellungen die <strong>Zugestellt</strong> sind</p>
    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>Bezahlt</th>
                <th>Datum</th>
                <th>Status</th>
            </tr>
        </table>
    </div>
    
    <div>
        <p>Die Zahl die zugestellte Bestellungen</p>
    </div>


    <div>
    
        <h2>Kunde in einer Stadt Suchen oder Kunden Liste</h2>

        <form method="POST">
            <input type="text" name="adresse">
            <input type="submit">
        </form>
    </div>
</body>
</html>