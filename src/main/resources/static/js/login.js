// SWITCH TRA LOGIN / REGISTER
const tabButtons = document.querySelectorAll(".tab-btn");
const forms = document.querySelectorAll("form");

tabButtons.forEach(btn => {
  btn.addEventListener("click", () => {
    tabButtons.forEach(b => b.classList.remove("active"));
    btn.classList.add("active");

    const targetSelector = btn.getAttribute("data-target");
    forms.forEach(f => {
      if ("#" + f.id === targetSelector) {
        f.classList.add("active");
      } else {
        f.classList.remove("active");
      }
    });
  });
});

// FUNZIONI DI SUPPORTO
function isEmailValid(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}
function showError(el, message) {
  el.textContent = message;
}
function clearError(el) {
  el.textContent = "";
}

// VALIDAZIONE LOGIN
const loginForm = document.getElementById("loginForm");
loginForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const emailInput = document.getElementById("loginEmail");
  const passInput = document.getElementById("loginPassword");

  const emailError = document.getElementById("loginEmailError");
  const passError = document.getElementById("loginPasswordError");

  let valid = true;

  if (!emailInput.value.trim()) {
    showError(emailError, "L'email è obbligatoria.");
    valid = false;
  } else if (!isEmailValid(emailInput.value.trim())) {
    showError(emailError, "Formato email non valido.");
    valid = false;
  } else {
    clearError(emailError);
  }

  if (!passInput.value.trim()) {
    showError(passError, "La password è obbligatoria.");
    valid = false;
  } else if (passInput.value.trim().length < 6) {
    showError(passError, "La password deve avere almeno 6 caratteri.");
    valid = false;
  } else {
    clearError(passError);
  }

  if (valid) {
    alert("✅ Accesso effettuato su RickyFit!");
  }
});

// VALIDAZIONE REGISTRAZIONE
const registerForm = document.getElementById("registerForm");
registerForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const nomeInput = document.getElementById("regNome");
  const cognomeInput = document.getElementById("regCognome");
  const emailInput = document.getElementById("regEmail");
  const passInput = document.getElementById("regPassword");

  const nomeError = document.getElementById("regNomeError");
  const cognomeError = document.getElementById("regCognomeError");
  const emailError = document.getElementById("regEmailError");
  const passError = document.getElementById("regPasswordError");

  let valid = true;

  if (!nomeInput.value.trim()) {
    showError(nomeError, "Il nome è obbligatorio.");
    valid = false;
  } else {
    clearError(nomeError);
  }

  if (!cognomeInput.value.trim()) {
    showError(cognomeError, "Il cognome è obbligatorio.");
    valid = false;
  } else {
    clearError(cognomeError);
  }

  if (!emailInput.value.trim()) {
    showError(emailError, "L'email è obbligatoria.");
    valid = false;
  } else if (!isEmailValid(emailInput.value.trim())) {
    showError(emailError, "Formato email non valido.");
    valid = false;
  } else {
    clearError(emailError);
  }

  if (!passInput.value.trim()) {
    showError(passError, "La password è obbligatoria.");
    valid = false;
  } else if (passInput.value.trim().length < 6) {
    showError(passError, "Minimo 6 caratteri.");
    valid = false;
  } else {
    clearError(passError);
  }

  if (valid) {
    alert("🎉 Benvenuto in RickyFit, " + nomeInput.value + "!");
  }
});
