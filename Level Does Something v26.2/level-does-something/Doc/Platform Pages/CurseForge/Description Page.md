<div align="center">
  <img src="../../Media/icon.png" width="128" alt="Level Does Something Icon">
</div>

<p align="center">
    <a href="https://discord.gg/EV99bgAFqb" target="_blank" rel="noopener"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Discord"></a>
</p>

# 📈 Level Does Something!

**Active Version Policy:** I build **1 JAR for 1 Version**. I only update and maintain the latest active Minecraft version (e.g. when 26.3 is released, 26.2 is retired). No backports or legacy version maintenance. Please do not ask.

> **Stop hoarding levels for nothing. Make your experience level do something.**

**Level Does Something!** translates your experience level points into real passive power. Hoard experience to scale up your break speed, attack damage, movement speed, luck, and maximum health. Complete with custom scaling curves, ambient particle effects, and acoustic milestone chimes.

Part of the **Instant Gratification Collection** — mods that respect the player's time and enhance vanilla loops.

---

## ✨ Features

### 📊 Level-Based Passive Attribute Boosts
Accumulate experience to scale up 5 core player attributes:
- ⛏️ **Block Break Speed**: Mine and harvest blocks faster.
- 🗡️ **Attack Damage**: Strike harder with every melee hit.
- 🏃 **Movement Speed**: Traverse the land quicker.
- 🍀 **Luck**: Get better loot from chests and fishing.
- ❤️ **Max Health**: Earn up to several extra hearts dynamically!

### 📐 Three Scaling Curves
Server operators can select how attributes scale:
- **Smooth Logarithmic** (Default): Fast scaling in early levels, tapering off gracefully for high levels. Prevents players from becoming overly overpowered.
- **Exponential Steps**: Modifiers double at power-of-two milestones (`Level 2, 4, 8, 16, 32...`) up to a configurable max tier.
- **Simple Linear**: A flat, direct multiplier per level.

### 🌟 Ambient Visual Aura & Milestones
- **Ambient Particle Aura**: Reaching Level 30 or higher surrounds you with a swirling aura of gold and green sparkles, visible to everyone.
- **Triumphant Chimes**: Milestone chimes play globally when you cross levels 30, 60, and 100.
- **XP Pitch-Shifting**: Experience orb pickup sounds shift higher in pitch as your level increases.

---

## ⚙️ Configuration (Native Game Rules)


<blockquote class="warning">
<strong>âš ï¸ Important: Config vs. In-Game GameRules</strong><br>
The global configuration file only defines <strong>default values for new worlds</strong> at creation time.
If you have <strong>already created/opened a world</strong>, changing the config file will have no effect. You must change the settings in-game using the <strong>Edit Game Rules</strong> UI screen or the <code>/gamerule</code> command.
</blockquote>
No config files needed on the server. Everything is handled via the native **Edit Game Rules** screen or commands:

```sql
/gamerule leveldoessomething:levelPowerCurveType 1       → 0 = Exponential, 1 = Logarithmic, 2 = Linear
/gamerule leveldoessomething:levelPowerBaseMultiplier 10   → Base scaling factor in basis points (10 = 0.1% per level)
/gamerule leveldoessomething:levelPowerMaxTier 10          → Maximum tier limit for the exponential curve
/gamerule leveldoessomething:levelPowerEnableAura true     → Toggle the ambient visual particle aura
/gamerule leveldoessomething:levelPowerEnableSoundPitch true → Toggle the XP sound pitch-shifts and milestone chimes
```

*Players can locally toggle particle visuals and sound shifts using Cloth Config and ModMenu to accommodate client performance.*

<hr>

<h2>☕ Support</h2>

<p>If you enjoy the <strong>Instant Gratification</strong> collection, consider fueling future updates!</p>

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&amp;logo=ko-fi&amp;logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

<blockquote><p><strong>🇮🇩 Indonesian Users:</strong> SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!</p></blockquote>

<blockquote><p><strong>Dedicated Server Hosting Partner:</strong><br>Looking for a high-performance server to host your community or play with friends? Check out <strong>BisectHosting</strong> for 1-click modpack installations, automated backups, and 24/7 dedicated customer support. Use promo code <strong><code>Dasik</code></strong> for 25% off your first month!</p></blockquote>

<h3>💬 Join the Community &amp; Get Support</h3>
<p>Looking for help, want to test early beta builds, or vote on upcoming features? Join our official Discord community!</p>
<p align="center">
  <a href="https://discord.gg/EV99bgAFqb" target="_blank" rel="noopener">
    <img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&amp;logo=discord&amp;logoColor=white" alt="Join Official Discord">
  </a>
</p>

<hr>

<h2>📜 Credits &amp; Modpack Permissions</h2>

<table>
  <thead>
    <tr>
      <th>Property</th>
      <th>Information</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Creator / Author</strong></td>
      <td><strong>Dasik</strong> (Rifaditya)</td>
    </tr>
    <tr>
      <td><strong>Community</strong></td>
      <td><a href="https://discord.gg/EV99bgAFqb" target="_blank" rel="noopener">Official Discord</a></td>
    </tr>
    <tr>
      <td><strong>Collection</strong></td>
      <td><a href="https://www.curseforge.com/members/dasikigaijin/projects">Instant Gratification</a></td>
    </tr>
    <tr>
      <td><strong>License</strong></td>
      <td><a href="https://www.gnu.org/licenses/gpl-3.0.html">GNU General Public License v3.0 (GPLv3)</a></td>
    </tr>
    <tr>
      <td><strong>Source Code</strong></td>
      <td><a href="https://github.com/Rifaditya/Instant-Gratification-level-does-something">GitHub - Rifaditya/Instant-Gratification-level-does-something</a></td>
    </tr>
    <tr>
      <td><strong>Issue Tracker</strong></td>
      <td><a href="https://github.com/Rifaditya/Instant-Gratification-level-does-something/issues">GitHub Issues</a></td>
    </tr>
    <tr>
      <td><strong>Documentation / Wiki</strong></td>
      <td><a href="https://github.com/Rifaditya/Instant-Gratification-level-does-something/wiki">GitHub Wiki</a></td>
    </tr>
  </tbody>
</table>

<blockquote>
  <p><strong>📦 Modpack Permissions &amp; Distribution:</strong><br>
  You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (<strong>CurseForge</strong> or <strong>Modrinth</strong>). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.</p>
  <p><strong>⚖️ License &amp; Fork Guidelines (No Zero-Change Re-uploads):</strong><br>
  This project is open-source under the <strong>GNU GPLv3</strong>. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports&mdash;provided your project remains open-source under GPLv3 with proper attribution.<br>
  <strong>However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.</strong></p>
</blockquote>

<hr>

<p align="center">
  <strong>Made with ❤️ for the Minecraft community</strong><br>
  <em>Part of the Instant Gratification Collection</em>
</p>
