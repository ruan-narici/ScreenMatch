import { useState } from 'react';
import styles from './Categories.module.scss';


export default function Categories() {
  const [showCategories, setShowCategories] = useState<boolean>(false);

  function changeVisibility() {
    if (!showCategories) {
      setShowCategories(true);
      return;
    }
    setShowCategories(false);
  }

  return (
    <div onMouseEnter={ changeVisibility }
      onMouseLeave={ changeVisibility }
      className={ styles.container }>
      <p className={ styles.container__title }>Categorias</p>
      <ul className={ !showCategories ? styles.dnone : styles.container__list }>
        <li className={ styles.item }>Comédia</li>
        <li className={ styles.item }>Ação</li>
        <li className={ styles.item }>Crime</li>
      </ul>
    </div>
  );
}