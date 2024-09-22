import styles from './Profile.module.scss';
import IconSearch from '@/assets/react.svg';


export default function Profile() {
  return (
    <div className={ styles.container }>
      <img className={ styles.container__img } src={ IconSearch } alt="Imagem de perfil" />
      <p>Ruan</p>
    </div>
  );
}