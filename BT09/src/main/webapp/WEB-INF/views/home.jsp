<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <script>
        function loadProducts() {
            fetch("/graphql", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({ query: "{ getAllProductsSorted { id title price category { name } user { fullname } } }" })
            })
            .then(res => res.json())
            .then(data => {
                let html = "<ul>";
                data.data.getAllProductsSorted.forEach(p => {
                    html += "<li>" + p.title + " - " + p.price + " | Category: " + p.category.name +
                            " | User: " + p.user.fullname + "</li>";
                });
                html += "</ul>";
                document.getElementById("products").innerHTML = html;
            });
        }

        window.onload = loadProducts;
    </script>
</head>
<body>
<h2>Product List (Price ascending)</h2>
<div id="products"></div>
</body>
</html>
