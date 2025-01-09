// Function to sort the table
function sortTable(tableId, n) {
    var table = document.getElementById(tableId).getElementsByTagName("tbody")[0]; // Select the table body
    var rows = Array.from(table.rows); // Convert the rows into an array
    var ascending = document.getElementById("sortBtn" + n).classList.contains("ascending"); // Check if the button for the current column is in ascending state
    
    // Sort the rows based on the content of the specified column (n)
    var sortedRows = rows.sort(function(a, b) {
        var aText = a.cells[n].innerText; // Get the text content of the cells in the specified column
        var bText = b.cells[n].innerText;
        
        function isNumeric(value) {
            return !isNaN(value) && value.trim() !== ''; // Checks if the value is a valid number
        }

        // Check if the column contains numeric values and convert if necessary
        if (isNumeric(aText) && isNumeric(bText)) {
            aText = parseFloat(aText.replace(/[^\d.-]/g, '')); // Remove non-numeric characters
            bText = parseFloat(bText.replace(/[^\d.-]/g, ''));
        }

        // Compare values and return the result based on the current sorting order
        if (ascending) {
            return aText > bText ? 1 : -1;
        } else {
            return aText < bText ? 1 : -1;
        }
    });

    // Rebuild the table with the sorted rows
    table.innerHTML = "";
    sortedRows.forEach(function(row) {
        table.appendChild(row);
    });

    // Toggle the button's state to reflect the current sorting order (ascending/descending)
    var buttons = document.querySelectorAll(".sortBtn");
    buttons.forEach(function(button) {
        button.classList.remove("ascending", "descending");
    });
    document.getElementById("sortBtn" + n).classList.toggle("ascending", !ascending);
    document.getElementById("sortBtn" + n).classList.toggle("descending", ascending);
}

// Function to export table
function exportTableToExcel(tableId, filename) {
    // Get the table by ID
    const table = document.getElementById(tableId);
    if (!table) {
        console.error(`Table with ID '${tableId}' does not exist.`);
        return;
    }

    // Convert the HTML table to an Excel worksheet
    const worksheet = XLSX.utils.table_to_sheet(table);

    // Create a new Excel workbook
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');

    // Save the Excel file
    XLSX.writeFile(workbook, filename);
}