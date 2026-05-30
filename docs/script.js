(function () {
  const searchInput = document.querySelector("#doc-search");
  const sections = Array.from(document.querySelectorAll(".doc-section"));
  const navLinks = Array.from(document.querySelectorAll(".sidebar nav a"));
  const sidebar = document.querySelector("#sidebar");
  const menuButton = document.querySelector(".menu-button");
  const backToTop = document.querySelector(".back-to-top");

  document.querySelectorAll("pre").forEach((pre) => {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "copy-button";
    button.textContent = "Copiar";
    button.addEventListener("click", async () => {
      const text = pre.innerText.replace("Copiar", "").trim();
      try {
        await navigator.clipboard.writeText(text);
        button.textContent = "Copiado";
        setTimeout(() => {
          button.textContent = "Copiar";
        }, 1200);
      } catch (_) {
        button.textContent = "Error";
        setTimeout(() => {
          button.textContent = "Copiar";
        }, 1200);
      }
    });
    pre.appendChild(button);
  });

  if (searchInput) {
    searchInput.addEventListener("input", () => {
      const query = searchInput.value.trim().toLowerCase();
      sections.forEach((section) => {
        const haystack = [
          section.dataset.search || "",
          section.innerText || ""
        ].join(" ").toLowerCase();
        section.classList.toggle("hidden-by-search", query.length > 0 && !haystack.includes(query));
      });
    });
  }

  if (menuButton && sidebar) {
    menuButton.addEventListener("click", () => {
      const open = sidebar.classList.toggle("open");
      menuButton.setAttribute("aria-expanded", String(open));
    });

    navLinks.forEach((link) => {
      link.addEventListener("click", () => {
        sidebar.classList.remove("open");
        menuButton.setAttribute("aria-expanded", "false");
      });
    });
  }

  const setActiveLink = () => {
    let current = sections[0]?.id;
    sections.forEach((section) => {
      const rect = section.getBoundingClientRect();
      if (rect.top <= 120 && rect.bottom > 120) {
        current = section.id;
      }
    });
    navLinks.forEach((link) => {
      link.classList.toggle("active", link.getAttribute("href") === `#${current}`);
    });
    if (backToTop) {
      backToTop.classList.toggle("visible", window.scrollY > 500);
    }
  };

  window.addEventListener("scroll", setActiveLink, { passive: true });
  setActiveLink();

  if (backToTop) {
    backToTop.addEventListener("click", () => {
      window.scrollTo({ top: 0, behavior: "smooth" });
    });
  }
})();
