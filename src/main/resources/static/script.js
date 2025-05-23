document.addEventListener('DOMContentLoaded', () => {
  const updateBtn = document.getElementById('updateHistoryBtn');
  const email = 'aleepintus@gmail.com'; // modifica con email reale
  const tbody = document.getElementById('historyBody');

  function loadHistory() {
    fetch(`/history-controller/find-history-by-email?email=${encodeURIComponent(email)}`)
      .then(response => response.json())
      .then(history => {
        tbody.innerHTML = '';

        if (!history.confirm || history.confirm.length === 0) {
          tbody.innerHTML = '<tr><td>Nessuna data di conferma presente</td></tr>';
          return;
        }

        history.confirm.forEach(dateStr => {
          const date = new Date(dateStr);
          const formatted = date.toLocaleString('it-IT', {
            year: 'numeric', month: '2-digit', day: '2-digit',
            hour: '2-digit', minute: '2-digit', second: '2-digit'
          });

          const tr = document.createElement('tr');
          const td = document.createElement('td');
          td.textContent = formatted;
          tr.appendChild(td);
          tbody.appendChild(tr);
        });
      })
      .catch(err => {
        console.error(err);
        tbody.innerHTML = '<tr><td>Errore nel caricamento della history</td></tr>';
      });
  }

  updateBtn.addEventListener('click', (e) => {
    e.preventDefault();

    const payload = {
      email: email,
      confirm: [new Date().toISOString()]
    };

    fetch(`/history-controller/update-history?email=${encodeURIComponent(email)}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
      if (!response.ok) throw new Error("Errore nell'aggiornamento");
      return response.text();
    })
    .then(() => {
      loadHistory(); // aggiorna la tabella
    })
    .catch(err => {
      console.error(err);
      alert("Errore durante la conferma");
    });
  });

  // Carica la tabella all'avvio
  loadHistory();
});
