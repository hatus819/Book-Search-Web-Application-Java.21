document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const searchButton = document.getElementById('searchButton');
    const resultsTableBody = document.querySelector('#resultsTable tbody');

    searchButton.addEventListener('click', performSearch);
    searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            performSearch();
        }
    });

    function performSearch() {
        const query = searchInput.value.trim();
        if (!query) {
            alert('Please enter a search query.');
            return;
        }

        fetch(`/booksearch/search?query=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                displayResults(data);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while searching.');
            });
    }

    function displayResults(books) {
        resultsTableBody.innerHTML = '';
        if (books.length === 0) {
            const row = resultsTableBody.insertRow();
            const cell = row.insertCell();
            cell.colSpan = 5;
            cell.textContent = 'No books found.';
            return;
        }

        books.forEach(book => {
            const row = resultsTableBody.insertRow();
            row.insertCell().textContent = book.title;
            row.insertCell().textContent = book.author;
            row.insertCell().textContent = book.genre;
            row.insertCell().textContent = book.published_year;
            row.insertCell().textContent = book.isbn;
        });
    }
});
